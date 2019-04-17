package com.springboot.mapper;

import com.springboot.dto.DrugProfileDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("drugMapper")
public interface DrugMapper {
    List<DrugProfileDto> findAll();
}
