package com.springboot.service.serviceImpl;

import com.springboot.commons.Constants;
import com.springboot.dto.DrugRestockDto;
import com.springboot.entity.BasicInformation;
import com.springboot.entity.DrugProfile;
import com.springboot.entity.DrugRestock;
import com.springboot.exception.GlobalException;
import com.springboot.repository.DrugProfileRepository;
import com.springboot.repository.DrugRestockRepository;
import com.springboot.service.DrugProfileService;
import com.springboot.service.DrugRestockService;
import com.springboot.service.SqeNoService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service("drugRestockServiceImpl")
@Transactional
public class DrugRestockServiceImpl implements DrugRestockService {

    @Autowired
    @Qualifier("drugRestockRepository")
    DrugRestockRepository restockRepository;

    @Autowired
    @Qualifier("drugProfileServiceImpl")
    DrugProfileService drugProfileService;

    @Autowired
    @Qualifier("drugProfileRepository")
    DrugProfileRepository drugProfileRepository;

    @Autowired
    @Qualifier("sqeNoServiceImpl")
    SqeNoService sqeNoService;

    @Autowired
    Mapper mapper;

    @Override
    public void save(DrugRestockDto restockDto,String userID) throws GlobalException {
        String drugID = restockDto.getDrugProfileDto().getId();
        Optional<DrugProfile> drugProfileOptional = drugProfileRepository.findById(drugID);
        if (!drugProfileOptional.isPresent()) {
            throw new GlobalException("400", "药物不存在");
        } else {
            DrugProfile drugProfile = drugProfileOptional.get();

            DrugRestock drugRestock = mapper.map(restockDto, DrugRestock.class);
            drugRestock.setId(sqeNoService.getSeqNo(Constants.RESTOCK));
            drugRestock.setDrugProfile(drugProfile);
            drugRestock.setBasicInformation(new BasicInformation());
            this.getModifiedInfo(drugRestock.getBasicInformation(), userID);

            restockRepository.save(drugRestock);

            //修改Drug_Profile的amount数量
            int amount = drugProfile.getAmount();
            amount += drugRestock.getAmount();
            drugProfile.setAmount(amount);

            drugProfileRepository.save(drugProfile);
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
