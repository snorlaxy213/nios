package com.springboot.dto;

import java.io.Serializable;

public class DiagnosisDto implements Serializable {

    private static final long serialVersionUID = -8115212120508315367L;

    private String id;

    private String description;

    private UserDto userDto;

    private PatientDto patientDto;

    private AppointmentDto appointmentDto;

    public DiagnosisDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public AppointmentDto getAppointmentDto() {
        return appointmentDto;
    }

    public void setAppointmentDto(AppointmentDto appointmentDto) {
        this.appointmentDto = appointmentDto;
    }
}
