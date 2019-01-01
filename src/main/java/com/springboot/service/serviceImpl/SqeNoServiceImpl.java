package com.springboot.service.serviceImpl;

import com.springboot.entity.TableIdentity;
import com.springboot.repository.SqeNoServiceRepository;
import com.springboot.service.SqeNoService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("sqeNoServiceImpl")
@Transactional
public class SqeNoServiceImpl implements SqeNoService {
    private static final Logger LOGGER = Logger.getLogger(SqeNoServiceImpl.class);

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    SqeNoServiceRepository sqeNoServiceRepository;

    @Override
    public String getSeqNo(String tableName) {

        Optional<TableIdentity> tableIdentity = sqeNoServiceRepository.findById(tableName);

        StringBuilder stringBuilder = new StringBuilder();
        tableIdentity.ifPresent(temp ->{
            String identityPrefix = temp.getIdentityPrefix();
            Long nextIdentity = temp.getNextIdentity();
            int keyLength = temp.getKeyLength();

            stringBuilder.append(identityPrefix);
            for (int i = 0; i < keyLength; i++) {
                stringBuilder.append("0");
            }
            stringBuilder.append(nextIdentity);
        });

        return stringBuilder.toString();
    }
}
