package com.springboot.dto;

import com.springboot.validation.EmailAddressValidation;
import com.springboot.validation.PhoneNumberValidation;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class UserDto extends BasicRowInfo implements Serializable {

    private static final long serialVersionUID = 1711743832907074561L;

    @NotEmpty(message = "用户编号不可以为空")
    private String id;

//    @UserNameValidation
    @NotEmpty(message = "用户名不可以为空")
    private String name;

    @EmailAddressValidation
    @NotEmpty(message = "邮箱不可以为空")
    private String email;

    @PhoneNumberValidation
    @NotEmpty(message = "手机号码不可以为空")
    private String mobile;

    private String password;

    @NotEmpty(message = "科室不可以为空")
    private String office;

    private Integer orderNum;

    private Integer currentNum;

    private Timestamp timestamp;

    private List<UserRoleDto> userRoleDtos;

    private List<AppointmentDto> appointmentDtos;

    private List<DiagnosisDto> diagnosisDtos;

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

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<UserRoleDto> getUserRoleDtos() {
        return userRoleDtos;
    }

    public void setUserRoleDtos(List<UserRoleDto> userRoleDtos) {
        this.userRoleDtos = userRoleDtos;
    }

    public List<AppointmentDto> getAppointmentDtos() {
        return appointmentDtos;
    }

    public void setAppointmentDtos(List<AppointmentDto> appointmentDtos) {
        this.appointmentDtos = appointmentDtos;
    }

    public List<DiagnosisDto> getDiagnosisDtos() {
        return diagnosisDtos;
    }

    public void setDiagnosisDtos(List<DiagnosisDto> diagnosisDtos) {
        this.diagnosisDtos = diagnosisDtos;
    }
}
