package com.springboot.dto;

import java.io.Serializable;
import java.util.Date;

public class AppointmentDto extends BasicRowInfo implements Serializable {
    private static final long serialVersionUID = 7996614077112855355L;

    private String id;

    private Date appointmentTime;

    private String duration;

    private String description;

    private UserDto userDto;

    private PatientDto patientDto;

    private DiagnosisDto diagnosisDto;

    public AppointmentDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public PatientDto getPatientDto() {
        return patientDto;
    }

    public void setPatientDto(PatientDto patientDto) {
        this.patientDto = patientDto;
    }

    public DiagnosisDto getDiagnosisDto() {
        return diagnosisDto;
    }

    public void setDiagnosisDto(DiagnosisDto diagnosisDto) {
        this.diagnosisDto = diagnosisDto;
    }
}
