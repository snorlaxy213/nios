package com.springboot.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class UserDto extends BasicRowInfo implements Serializable {

    private static final long serialVersionUID = 1711743832907074561L;

    @NotEmpty(message = "id cannot be empty")
    @Length(max = 12)
    private String id;

    @NotEmpty(message = "name cannot be empty")
    @Length(max = 100)
    private String name;

    @NotEmpty(message = "email cannot be empty")
    @Length(max = 50)
    private String email;

    @NotEmpty(message = "mobile cannot be empty")
    @Length(max = 100)
    private String mobile;

    @Length(max = 200)
    private String password;

    private Timestamp timestamp;

    private List<UserRoleDto> userRoles;

    public UserDto() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<UserRoleDto> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoleDto> userRoles) {
        this.userRoles = userRoles;
    }
}
