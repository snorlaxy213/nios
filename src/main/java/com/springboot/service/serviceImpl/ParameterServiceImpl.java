package com.springboot.service.serviceImpl;

import com.springboot.entity.Parameter;
import com.springboot.repository.ParameterRepository;
import com.springboot.service.ParameterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("parameterServiceImpl")
@Transactional
public class ParameterServiceImpl implements ParameterService {

    private static final Logger LOGGER = Logger.getLogger(ParameterServiceImpl.class);

    @Autowired
    @Qualifier("parameterRepository")
    ParameterRepository parameterRepository;

    public int getValue(String Parameter) {
        Parameter parameter = parameterRepository.findByParameter(Parameter);
        int value = parameter.getValue();

        parameter.setValue(value+1);
        parameterRepository.save(parameter);
        return value;
    }

    public void insertValue(Parameter parameter) {
        try {
            parameterRepository.save(parameter);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }
}
