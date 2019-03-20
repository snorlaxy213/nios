package com.springboot.dto;

import com.springboot.entity.BasicInformation;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class UserRoleDto extends BasicRowInfo implements Serializable {

    private static final long serialVersionUID = 2870638623857572763L;

    private String id;

    @NotEmpty(message = "UserRoleName")
    private String name;

    @NotEmpty(message = "Status")
    private String status;

    private List<UserDto> userDtos;

    private BasicInformation basicInformation;

    private Timestamp timestamp;

    public UserRoleDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }

    public BasicInformation getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(BasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
