package com.springboot.service.serviceImpl;

import com.springboot.dto.DiagnosisDto;
import com.springboot.dto.DrugProfileDto;
import com.springboot.dto.DrugStockDto;
import com.springboot.entity.BasicInformation;
import com.springboot.entity.Diagnosis;
import com.springboot.entity.DrugProfile;
import com.springboot.entity.DrugStock;
import com.springboot.repository.DrugStockRepository;
import com.springboot.service.DiagnosisService;
import com.springboot.service.DrugProfileService;
import com.springboot.service.DrugStockService;
import com.springboot.service.SqeNoService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service("drugStockServiceImpl")
@Transactional
public class DrugStockServiceImpl implements DrugStockService {

    private static final String tableIdentify = "DRUGSTOCK";

    @Autowired
    @Qualifier("drugStockRepository")
    DrugStockRepository drugStockRepository;

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    @Qualifier("sqeNoServiceImpl")
    SqeNoService sqeNoService;

    @Autowired
    @Qualifier("diagnosisServiceImpl")
    DiagnosisService diagnosisService;

    @Autowired
    @Qualifier("drugProfileServiceImpl")
    DrugProfileService drugProfileService;

    @Override
    public void save(DrugStockDto drugStockDto, String userId) {
        DrugStock drugStock = mapper.map(drugStockDto,DrugStock.class);

        drugStock.setId(sqeNoService.getSeqNo(tableIdentify));

        DrugProfileDto drugProfileDto = drugProfileService.findById(drugStockDto.getDrugProfileDto().getId());
        DrugProfile drugProfile = mapper.map(drugProfileDto,DrugProfile.class);
        drugStock.setAmount(drugProfile.getDefaultQuantity());
        drugStock.setDrugProfile(drugProfile);

        DiagnosisDto diagnosisDto = diagnosisService.findById(drugStockDto.getDiagnosisDto().getId());
        Diagnosis diagnosis = mapper.map(diagnosisDto,Diagnosis.class);
        drugStock.setDiagnosis(diagnosis);
        drugStock.setBasicInformation(new BasicInformation());
        this.getModifiedInfo(drugStock.getBasicInformation(), userId);

        drugStockRepository.save(drugStock);
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
