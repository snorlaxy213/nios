package com.springboot.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class UserDto extends BasicRowInfo implements Serializable {

    private static final long serialVersionUID = 1711743832907074561L;

    @NotEmpty(message = "UserID")
    @Length(max = 12)
    private String id;

    @NotEmpty(message = "UserName")
    @Length(max = 100)
    private String name;

    @NotEmpty(message = "Email")
    @Length(max = 50)
    private String email;

    @NotEmpty(message = "Mobile")
    @Length(max = 100)
    private String mobile;

    @Length(max = 200)
    private String password;

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
