package com.springboot.service.serviceImpl;

import com.springboot.dto.AppointmentDto;
import com.springboot.dto.PatientDto;
import com.springboot.dto.UserDto;
import com.springboot.entity.Appointment;
import com.springboot.entity.BasicInformation;
import com.springboot.entity.Patient;
import com.springboot.entity.User;
import com.springboot.repository.AppointmentRepository;
import com.springboot.service.AppointmentService;
import com.springboot.service.PatientService;
import com.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<AppointmentDto> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();

        if (appointments.size() > 0) {
            List<AppointmentDto> appointmentDtos = new ArrayList<>();
            appointments.forEach(appointment -> appointmentDtos.add(mapper.map(appointment, AppointmentDto.class)));
            return appointmentDtos;
        } else return null;
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
    public void save(AppointmentDto appointmentDto) {
        try {
            Long count = appointmentRepository.countById(appointmentDto.getId());

            if (count > 0) {
                Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentDto.getId());

                Appointment appointment = appointmentOptional.get();
                appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
                appointment.setDescription(appointmentDto.getDescription());
                appointment.setDuration(appointmentDto.getDuration());

                UserDto userDto = userService.findById(appointmentDto.getUserDto().getId());
                User user = mapper.map(userDto, User.class);
                appointment.setUser(user);

                PatientDto patientDto = patientService.findById(appointmentDto.getPatientDto().getId());
                Patient patient = mapper.map(patientDto,Patient.class);
                appointment.setPatient(patient);
                this.getModifiedInfo(user.getBasicInformation(), "1", 1);

                appointmentRepository.save(appointment);
            } else {
                Appointment appointment = mapper.map(appointmentDto,Appointment.class);
                UserDto userDto = userService.findById(appointmentDto.getUserDto().getId());
                User user = mapper.map(userDto, User.class);
                appointment.setUser(user);
                appointment.setBasicInformation(new BasicInformation());
                this.getModifiedInfo(appointment.getBasicInformation(),"1",1);

                appointmentRepository.save(appointment);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private BasicInformation getModifiedInfo(BasicInformation basicInformation, String userID, Integer ClinicCode) {
        if (basicInformation.getCreateBy() != null) {
            basicInformation.setUpdateBy(userID);
            basicInformation.setUpdateDtm(new Date());
            basicInformation.setUpdateClinic(ClinicCode);
        }else {
            basicInformation.setCreateBy(userID);
            basicInformation.setCreateDtm(new Date());
            basicInformation.setCreateClinic(ClinicCode);
            basicInformation.setUpdateBy(userID);
            basicInformation.setUpdateDtm(new Date());
            basicInformation.setUpdateClinic(ClinicCode);
        }

        return basicInformation;
    }
}
