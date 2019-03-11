package com.springboot.dto;

import com.springboot.entity.BasicInformation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class PatientDto implements Serializable {

    private static final long serialVersionUID = -2284174034487559691L;

    private String id;

    private String name;

    private Long age;

    private String gender;

    private String email;

    private String mobile;

    private BasicInformation basicInformation;

    private Timestamp timestamp;

    private List<AppointmentDto> appointmentDtos;

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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public List<AppointmentDto> getAppointmentDtos() {
        return appointmentDtos;
    }

    public void setAppointmentDtos(List<AppointmentDto> appointmentDtos) {
        this.appointmentDtos = appointmentDtos;
    }
}
