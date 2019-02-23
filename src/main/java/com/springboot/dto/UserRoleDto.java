package com.springboot.dto;

import com.springboot.entity.BasicInformation;
import com.springboot.entity.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class UserRoleDto extends BasicRowInfo implements Serializable {

    private static final long serialVersionUID = 2870638623857572763L;

    private String id;

    private String name;

    private String status;

    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
