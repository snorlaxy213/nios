package com.springboot.service.serviceImpl;

import com.springboot.dto.Message;
import com.springboot.dto.UserDto;
import com.springboot.entity.BasicInfomation;
import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private static final String YYYYMMDD = "yyyy-MM-dd";


    @Autowired
    UserRepository userRepository;

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = mapper.map(user, UserDto.class);
            userDtos.add(userDto);
        });

        return userDtos;
    }

    @Override
    public Message findAllWithPage(Integer pageNumber, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageNumber, pageSize, sort);

        Page<User> userPages = userRepository.findAll(pageable);

        List<User> users = userPages.getContent();

        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = mapper.map(user, UserDto.class);
            userDtos.add(userDto);
        });

        Integer totalPage = userPages.getTotalPages();
        Long totalElement = userPages.getTotalElements();

        return Message.success().add("list", userDtos).add("totalPage", totalPage).add("totalElement", totalElement);
    }


    @Override
    public Message findById(String id) {
        Optional<User> user = userRepository.findById(id);

        UserDto userDto = mapper.map(user.get(), UserDto.class);

        if (user.isPresent()) {
            return Message.success().add("user", userDto);
        } else {
            return Message.fail();
        }


    }

    @Override
    public Message save(UserDto userDto) {

        if (validation(userDto)) {
            Message message = Message.fail();
            message.setMsg("input error");
            return message;
        }

        Long count = userRepository.countById(userDto.getId());

        if (count > 0) {
            Optional<User> userOptional = userRepository.findById(userDto.getId());

            if (userOptional.isPresent()) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYYMMDD);

                User user = userOptional.get();
                user.setName(userDto.getName());
                user.setEmail(userDto.getEmail());
                user.setMobile(userDto.getMobile());
                Date Effective = null;
                Date Expiry = null;
                try {
                    Effective = simpleDateFormat.parse(userDto.getEffectiveStr());
                    Expiry = simpleDateFormat.parse(userDto.getExpiryStr());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                user.setEffective(Effective);
                user.setExpiry(Expiry);

                this.getModifiedInfo(user.getBasicInfomation(), "1", 1);

                userRepository.save(user);
                return Message.success();
            }
        } else {
            User user = mapper.map(userDto, User.class);
            this.getModifiedInfo(user.getBasicInfomation(), "1", 1);
            user.setPassword("123456");
            userRepository.save(user);
            return Message.success();
        }
        return Message.success();
    }

    boolean validation(UserDto userDto) {
        if (userDto.getId() == null || userDto.getEffectiveStr() == null || userDto.getEmail() == null
                || userDto.getName() == null) {
            return true;
        } else {
            return false;
        }
    }

    private BasicInfomation getModifiedInfo(BasicInfomation aBscRwInf, String lUID, Integer lClinicCode) {

        if (aBscRwInf != null) {
            if (aBscRwInf.getCreateBy() == null) {
                aBscRwInf.setCreateBy(lUID);
                aBscRwInf.setCreateDtm(new Date());
                aBscRwInf.setCreateClinic(lClinicCode);
                aBscRwInf.setUpdateBy(lUID);
                aBscRwInf.setUpdateDtm(new Date());
                aBscRwInf.setUpdateClinic(lClinicCode);
            } else {
                aBscRwInf.setUpdateBy(lUID);
                aBscRwInf.setUpdateDtm(new Date());
                aBscRwInf.setUpdateClinic(lClinicCode);
            }
        }

        return aBscRwInf;
    }
}
