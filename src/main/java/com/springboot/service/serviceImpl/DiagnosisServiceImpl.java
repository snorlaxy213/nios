package com.springboot.service.serviceImpl;

import com.springboot.commons.CommonTableUtils;
import com.springboot.dto.DiagnosisDto;
import com.springboot.dto.PatientDto;
import com.springboot.dto.UserDto;
import com.springboot.entity.BasicInformation;
import com.springboot.entity.Diagnosis;
import com.springboot.entity.Patient;
import com.springboot.entity.User;
import com.springboot.exception.GlobalException;
import com.springboot.repository.DiagnosisRepository;
import com.springboot.service.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("diagnosisServiceImpl")
@Transactional
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    @Qualifier("diagnosisRepository")
    DiagnosisRepository diagnosisRepository;

    @Autowired
    @Qualifier("patientServiceImpl")
    PatientService patientService;

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("appointmentServiceImpl")
    AppointmentService appointmentService;

    @Autowired
    @Qualifier("sqeNoServiceImpl")
    SqeNoService sqeNoService;

    @Override
    public List<DiagnosisDto> findAll() {
        List<Diagnosis> diagnoses = diagnosisRepository.findAll();

        List<DiagnosisDto> diagnosisDtos = new ArrayList<>();
        diagnoses.forEach(diagnosis -> diagnosisDtos.add(mapper.map(diagnosis,DiagnosisDto.class)));
        return diagnosisDtos;
    }

    @Override
    public DiagnosisDto findById(String id) {
        Optional<Diagnosis> diagnosisOptional = diagnosisRepository.findById(id);

        if (diagnosisOptional.isPresent()) {
            DiagnosisDto diagnosisDto = mapper.map(diagnosisOptional.get(),DiagnosisDto.class);
            return diagnosisDto;
        } else return null;
    }

    @Override
    public void save(DiagnosisDto diagnosisDto) {
        if (diagnosisDto.getUserDto() == null) {
            throw new GlobalException("400","user is null");
        } else if (diagnosisDto.getPatientDto() == null) {
            throw new GlobalException("400","patient is null");
        }

        Diagnosis diagnosis = mapper.map(diagnosisDto,Diagnosis.class);

        UserDto userDto = userService.findById(diagnosisDto.getUserDto().getId());
        diagnosis.setUser(mapper.map(userDto, User.class));

        PatientDto patientDto = patientService.findById(diagnosisDto.getPatientDto().getId());
        diagnosis.setPatient(mapper.map(patientDto, Patient.class));

        diagnosis.setId(sqeNoService.getSeqNo(CommonTableUtils.DIAGNOSIS));
        diagnosis.setBasicInformation(new BasicInformation());
        this.getModifiedInfo(diagnosis.getBasicInformation(), "1", 1);

        diagnosisRepository.save(diagnosis);
        appointmentService.delete(diagnosisDto.getAppointmentDto().getId());

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
