package com.springboot.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.commons.AliyunOSSUtil;
import com.springboot.commons.CommonTableUtils;
import com.springboot.commons.ExcelUtil;
import com.springboot.dto.DrugProfileDto;
import com.springboot.entity.BasicInformation;
import com.springboot.entity.DrugProfile;
import com.springboot.mapper.DrugMapper;
import com.springboot.repository.DrugProfileRepository;
import com.springboot.service.DrugProfileService;
import com.springboot.service.SqeNoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("drugProfileServiceImpl")
@Transactional
public class DrugProfileServiceImpl implements DrugProfileService {

    private static final Logger LOGGER = Logger.getLogger(DrugProfileServiceImpl.class);

    @Autowired
    @Qualifier("drugProfileRepository")
    DrugProfileRepository drugProfileRepository;

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    @Qualifier("sqeNoServiceImpl")
    SqeNoService sqeNoService;

    @Autowired
    @Qualifier("drugMapper")
    DrugMapper drugMapper;

    @Autowired
    AliyunOSSUtil aliyunOSSUtil;

    @Override
    public List<DrugProfileDto> findAll() {
        List<DrugProfile> drugProfiles = drugProfileRepository.findAll();

        List<DrugProfileDto> drugProfileDtos = new ArrayList<>();
        drugProfiles.forEach(drugProfile -> drugProfileDtos.add(mapper.map(drugProfile,DrugProfileDto.class)));
        return drugProfileDtos;
    }

    @Override
    public PageInfo findAllByMybatis(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<DrugProfileDto> drugProfileDtos = drugMapper.findAll();
        PageInfo<DrugProfileDto> drugProfileDtoPageInfo = new PageInfo<>(drugProfileDtos,5);

        return drugProfileDtoPageInfo;
    }

    @Override
    public DrugProfileDto findById(String id) {
        Optional<DrugProfile> drugProfileOptional = drugProfileRepository.findById(id);

        if (drugProfileOptional.isPresent()) {
            return mapper.map(drugProfileOptional.get(),DrugProfileDto.class);
        } else return null;
    }

    @Override
    public void save(DrugProfileDto drugProfileDto, String userId) {
        try {
            Long count = drugProfileRepository.countById(drugProfileDto.getId());
            if (count > 0) {
                Optional<DrugProfile> drugProfileOptional = drugProfileRepository.findById(drugProfileDto.getId());
                DrugProfile drugProfile = drugProfileOptional.get();

                drugProfile.setName(drugProfileDto.getName());
                drugProfile.setType(drugProfileDto.getType());
                drugProfile.setDefaultQuantity(drugProfileDto.getDefaultQuantity());
                drugProfile.setDescription(drugProfileDto.getDescription());
                drugProfile.setAmount(drugProfileDto.getAmount());
                drugProfile.setUnit(drugProfileDto.getUnit());
                drugProfile.setStatus(drugProfileDto.getStatus());
                drugProfile.setUrl(null);
                this.getModifiedInfo(drugProfile.getBasicInformation(), userId);

                drugProfileRepository.save(drugProfile);
            } else {
                DrugProfile drugProfile = mapper.map(drugProfileDto,DrugProfile.class);
                drugProfile.setId(sqeNoService.getSeqNo(CommonTableUtils.DRUG));
                drugProfile.setUrl(null);

                drugProfile.setBasicInformation(new BasicInformation());
                this.getModifiedInfo(drugProfile.getBasicInformation(), userId);

                drugProfileRepository.save(drugProfile);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void save(DrugProfileDto drugProfileDto, String userId, boolean batchSave) {
        try {
            Long count = drugProfileRepository.countById(drugProfileDto.getId());
            if (count > 0) {
                Optional<DrugProfile> drugProfileOptional = drugProfileRepository.findById(drugProfileDto.getId());
                DrugProfile drugProfile = drugProfileOptional.get();

                drugProfile.setName(drugProfileDto.getName());
                drugProfile.setType(drugProfileDto.getType());
                drugProfile.setDefaultQuantity(drugProfileDto.getDefaultQuantity());
                drugProfile.setDescription(drugProfileDto.getDescription());
                drugProfile.setAmount(drugProfileDto.getAmount());
                drugProfile.setUnit(drugProfileDto.getUnit());
                drugProfile.setStatus(drugProfileDto.getStatus());
                drugProfile.setUrl(drugProfileDto.getUrl());
                this.getModifiedInfo(drugProfile.getBasicInformation(), userId);

                drugProfileRepository.save(drugProfile);
            } else {
                DrugProfile drugProfile = mapper.map(drugProfileDto,DrugProfile.class);
                drugProfile.setId(sqeNoService.getSeqNo(CommonTableUtils.DRUG));

                drugProfile.setBasicInformation(new BasicInformation());
                this.getModifiedInfo(drugProfile.getBasicInformation(), userId);

                drugProfileRepository.save(drugProfile);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void batchSave(MultipartFile file,String userId) {
        try {
            LOGGER.info("uploadFile start :" + file.getOriginalFilename());
            String filename = file.getOriginalFilename();

            if (file != null) {
                if (StringUtils.isNotEmpty(filename.trim())) {
                    File fileTemp = new File(filename);
                    FileOutputStream os = new FileOutputStream(fileTemp);
                    os.write(file.getBytes());
                    os.close();

                    List<DrugProfileDto> list = ExcelUtil.importExcel(fileTemp);
                    String Url = aliyunOSSUtil.upLoad(fileTemp);
                    list.forEach(drugProfileDto -> {
                        drugProfileDto.setUrl(Url);
                        save(drugProfileDto, userId, true);
                    });
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(List<String> drugIdList) {
        try {
            drugIdList.forEach(userId -> drugProfileRepository.deleteById(userId));
        } catch (Exception ex) {
            LOGGER.error("delete fail",ex);
            throw ex;
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
