package com.springboot.service.serviceImpl;

import com.springboot.commons.Constants;
import com.springboot.dto.AppointmentDto;
import com.springboot.dto.PatientDto;
import com.springboot.dto.UserDto;
import com.springboot.entity.Appointment;
import com.springboot.entity.BasicInformation;
import com.springboot.entity.Patient;
import com.springboot.entity.User;
import com.springboot.repository.AppointmentRepository;
import com.springboot.service.*;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("appointmentServiceImpl")
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger LOGGER = Logger.getLogger(AppointmentServiceImpl.class);

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    @Qualifier("appointmentRepository")
    AppointmentRepository appointmentRepository;

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("patientServiceImpl")
    PatientService patientService;

    @Autowired
    @Qualifier("sqeNoServiceImpl")
    SqeNoService sqeNoService;

    @Autowired
    @Qualifier("parameterServiceImpl")
    ParameterService parameterService;

    @Override
    public List<AppointmentDto> findAll() {
        List<Appointment> appointments = appointmentRepository.findAllByStatus("Y");

        if (appointments.size() > 0) {
            List<AppointmentDto> appointmentDtos = new ArrayList<>();
            appointments.forEach(appointment -> appointmentDtos.add(mapper.map(appointment, AppointmentDto.class)));
            return appointmentDtos;
        } else return null;
    }

    @Override
    public long getCount() {
        long count = appointmentRepository.countByStatus("Y");
        return count;
    }

    @Override
    public AppointmentDto findByID(String id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);

        if (appointment.isPresent()) {
            Appointment temp = appointment.get();
            return mapper.map(temp,AppointmentDto.class);
        } else return null;
    }

    @Override
    public void save(AppointmentDto appointmentDto, String userId) throws Exception {
        try {
            Long count = appointmentRepository.countById(appointmentDto.getId());

            if (count > 0) {
                Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentDto.getId());

                Appointment appointment = appointmentOptional.get();
                appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
                appointment.setDescription(appointmentDto.getDescription());

                UserDto userDto = userService.findById(appointmentDto.getUserDto().getId());
                User user = mapper.map(userDto, User.class);
                appointment.setUser(user);

                PatientDto patientDto = patientService.findById(appointmentDto.getPatientDto().getId());
                Patient patient = mapper.map(patientDto,Patient.class);
                appointment.setPatient(patient);
                this.getModifiedInfo(user.getBasicInformation(), userId);

                appointmentRepository.save(appointment);
            } else {
                Appointment appointment = mapper.map(appointmentDto,Appointment.class);
                appointment.setId(sqeNoService.getSeqNo(Constants.APPOINTMENT));
                appointment.setSequence(userService.getCurrentNum(appointmentDto.getUserDto().getId()));
                appointment.setStatus("Y");

                UserDto userDto = userService.findById(appointmentDto.getUserDto().getId());
                User user = mapper.map(userDto, User.class);
                appointment.setUser(user);

                PatientDto patientDto = patientService.findById(appointmentDto.getPatientDto().getId());
                Patient  patient = mapper.map(patientDto,Patient.class);
                appointment.setPatient(patient);

                appointment.setBasicInformation(new BasicInformation());
                this.getModifiedInfo(appointment.getBasicInformation(),userId);

                appointmentRepository.save(appointment);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(String Id) {
        try {
            Optional<Appointment> appointmentOptional = appointmentRepository.findById(Id);
            appointmentOptional.ifPresent(appointment -> {
                appointment.setStatus("N");
                User user = appointment.getUser();
                appointmentRepository.save(appointment);

                userService.descCurrentNum(user.getId());
            });
        } catch (Exception ex) {
            LOGGER.error("delete fail",ex);
            throw ex;
        }
    }

    @Override
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void deleteSchedule() {
        System.out.println("执行测试cron时间： " + new Date(System.currentTimeMillis()));
        List<Appointment> appointments = appointmentRepository.findAllByStatus("Y");
        appointments.forEach(appointment -> {
            Date appointmentTime = appointment.getAppointmentTime();
            Calendar appointmentCad = Calendar.getInstance();
            Calendar nowCad = Calendar.getInstance();
            appointmentCad.setTime(appointmentTime);

//            int appointmentMinute = appointmentCad.get(Calendar.MINUTE);
//            int nowMinute = nowCad.get(Calendar.MINUTE);
            long appointmentMinute = appointmentCad.getTimeInMillis();
            long nowMinute = nowCad.getTimeInMillis();

            if ((nowMinute-appointmentMinute)/(1000*60) > 5 ) {
                delete(appointment.getId());
                System.out.println("预约："+ appointment.getId() +"已超时");
            }
        });
    }

    private BasicInformation getModifiedInfo(BasicInformation basicInformation, String userID) {
        if (basicInformation.getCreateBy() != null) {
            basicInformation.setUpdateBy(userID);
            basicInformation.setUpdateDtm(new Date());
        }else {
            basicInformation.setCreateBy(userID);
            basicInformation.setCreateDtm(new Date());
            basicInformation.setUpdateBy(userID);
            basicInformation.setUpdateDtm(new Date());
        }

        return basicInformation;
    }
}
