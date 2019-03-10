package com.springboot.dto;

import java.io.Serializable;
import java.util.Date;

public class AppointmentDto extends BasicRowInfo implements Serializable {
    private static final long serialVersionUID = 7996614077112855355L;

    private String appointmentID;

    private String doctor;

    private Date appointmentTime;

    private String duration;

    private String description;

    private UserDto userDto;

    public AppointmentDto() {
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
