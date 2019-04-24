package com.springboot.service.serviceImpl;

import com.springboot.dto.DiagnosisDto;
import com.springboot.dto.DispensingDrugDto;
import com.springboot.dto.DispensingDto;
import com.springboot.dto.DrugProfileDto;
import com.springboot.exception.GlobalException;
import com.springboot.mapper.DispensingMapper;
import com.springboot.service.DiagnosisService;
import com.springboot.service.DispensingService;
import com.springboot.service.DrugProfileService;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(DispensingServiceImpl.class);

    @Autowired
    @Qualifier("dispensingMapper")
    DispensingMapper dispensingMapper;

    @Autowired
    @Qualifier("drugProfileServiceImpl")
    DrugProfileService drugProfileService;

    @Autowired
    @Qualifier("diagnosisServiceImpl")
    DiagnosisService diagnosisService;

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

    @Override
    public String dispensing(String diagnosisID, String userId) throws Exception {
        try {
            List<Map<String, Object>> mapList = dispensingMapper.findByDiagnosisID(diagnosisID);
            DiagnosisDto diagnosisDto = diagnosisService.findById(diagnosisID);

            if (diagnosisDto.getStatus().equals("N")){
                return "已配药完成,无法重复配药";
            }

            List<DispensingDrugDto> dispensingDrugDtos = new ArrayList<>();
            for (int i = 0; i < mapList.size(); i++) {
                Map<String, Object> objectMapTemp = mapList.get(i);
                DispensingDrugDto dispensingDrugDto = new DispensingDrugDto();
                dispensingDrugDto.setDrugName((String) objectMapTemp.get("drug_name"));
                dispensingDrugDto.setAmount((int) objectMapTemp.get("amount"));
                dispensingDrugDto.setPrice((BigDecimal) objectMapTemp.get("price"));

                dispensingDrugDtos.add(dispensingDrugDto);
            }

            for (DispensingDrugDto dispensingDrugDto : dispensingDrugDtos) {
                DrugProfileDto drugProfileDto = drugProfileService.findByNAME(dispensingDrugDto.getDrugName());

                int temp = drugProfileDto.getAmount() - dispensingDrugDto.getAmount();
                if (temp < 0) {
                    return dispensingDrugDto.getDrugName() + "数量不足,缺少数量" + (-temp);
                } else {
                    drugProfileDto.setAmount(temp);
                    drugProfileService.save(drugProfileDto, userId);
                    diagnosisDto.setStatus("N");
                    diagnosisService.save(diagnosisDto, userId);
                }
            }
            return "配药成功";
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new GlobalException();
        }
    }
}
