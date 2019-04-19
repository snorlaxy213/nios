package com.springboot.service.serviceImpl;

import com.springboot.dto.DispensingDrugDto;
import com.springboot.dto.DispensingDto;
import com.springboot.mapper.DispensingMapper;
import com.springboot.service.DispensingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("dispensingServiceImpl")
@Transactional
public class DispensingServiceImpl implements DispensingService {

    @Autowired
    @Qualifier("dispensingMapper")
    DispensingMapper dispensingMapper;

    @Override
    public DispensingDto findByDiagnosisID(String diagnosisID) throws Exception {
        DispensingDto dispensingDto = new DispensingDto();
        List<Map<String, Object>> mapList = dispensingMapper.findByDiagnosisID(diagnosisID);
        Map<String, Object> objectMap = mapList.get(0);
        dispensingDto.setUserName((String) objectMap.get("name"));
        dispensingDto.setPatientName((String) objectMap.get("p_name"));
        dispensingDto.setDescription((String) objectMap.get("description"));

        BigDecimal total = new BigDecimal("0.0");
        List<DispensingDrugDto> dispensingDrugDtos = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> objectMapTemp = mapList.get(i);
            DispensingDrugDto dispensingDrugDto = new DispensingDrugDto();
            dispensingDrugDto.setDrugName((String) objectMapTemp.get("drug_name"));
            dispensingDrugDto.setAmount((int) objectMapTemp.get("amount"));
            dispensingDrugDto.setPrice((BigDecimal) objectMapTemp.get("price"));
            BigDecimal totalTemp = (BigDecimal) objectMapTemp.get("total");
            total = total.add(totalTemp);

            dispensingDrugDtos.add(dispensingDrugDto);
        }
        dispensingDto.setTotal(total);
        dispensingDto.setDispensingDrugDtos(dispensingDrugDtos);
        return dispensingDto;
    }
}
