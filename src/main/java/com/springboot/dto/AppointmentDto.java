package com.springboot.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class AppointmentDto extends BasicRowInfo implements Serializable {
    private static final long serialVersionUID = 7996614077112855355L;

    private String id;

    private Date appointmentTime;

    private String description;

    private Integer sequence;

    private UserDto userDto;

    private PatientDto patientDto;

    private DiagnosisDto diagnosisDto;

    private Timestamp timestamp;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


}
