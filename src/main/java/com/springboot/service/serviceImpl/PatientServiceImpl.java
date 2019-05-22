package com.springboot.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.commons.Constants;
import com.springboot.dto.PatientDto;
import com.springboot.entity.BasicInformation;
import com.springboot.entity.Patient;
import com.springboot.mapper.RegistrationMapper;
import com.springboot.repository.PatientRepository;
import com.springboot.service.PatientService;
import com.springboot.service.SqeNoService;
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

@Service("patientServiceImpl")
@Transactional
public class PatientServiceImpl implements PatientService {
    private static final Logger LOGGER = Logger.getLogger(PatientServiceImpl.class);

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    @Qualifier("patientRepository")
    PatientRepository patientRepository;

    @Autowired
    @Qualifier("sqeNoServiceImpl")
    SqeNoService sqeNoService;

    @Autowired
    @Qualifier("registrationMapper")
    RegistrationMapper registrationMapper;

    @Override
    public List<PatientDto> findAll() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientDto> patientDtos = new ArrayList<>();
        patients.forEach(patient -> {
            PatientDto patientDto = mapper.map(patient,PatientDto.class);
            patientDtos.add(patientDto);
        });

        return patientDtos;
    }

    @Override
    public PageInfo findAllByMybatis(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<PatientDto> patientDtos = registrationMapper.findAll();
        PageInfo<PatientDto> patientDtoPageInfo = new PageInfo<>(patientDtos,5);

        return patientDtoPageInfo;
    }

    @Override
    public PatientDto findById(String id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);

        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            return mapper.map(patient,PatientDto.class);
        }else return null;
    }

    @Override
    public void save(PatientDto patientDto, String userId) {
        try {

            Long count = patientRepository.countById(patientDto.getId());

            if (count > 0) {
                Optional<Patient> optional = patientRepository.findById(patientDto.getId());

                Patient patient = optional.get();
                patient.setName(patientDto.getName());
                patient.setAge(patientDto.getAge());
                patient.setGender(patientDto.getGender());
                patient.setMobile(patientDto.getMobile());
                patient.setEmail(patientDto.getEmail());
                patient.setStatus(patientDto.getStatus());
                this.getModifiedInfo(patient.getBasicInformation(),userId);

                patientRepository.save(patient);
            } else {
                Patient patient = mapper.map(patientDto,Patient.class);
                patient.setId(sqeNoService.getSeqNo(Constants.PATIENT));

                patient.setBasicInformation(new BasicInformation());
                this.getModifiedInfo(patient.getBasicInformation(),userId);

                patientRepository.save(patient);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(String id, String userId) {
        try {
            Optional<Patient> patientOptional = patientRepository.findById(id);

            if (patientOptional.isPresent()) {
                Patient patient = patientOptional.get();
                patient.setStatus("N");

                this.getModifiedInfo(patient.getBasicInformation(),userId);

                patientRepository.save(patient);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
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
