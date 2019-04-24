package com.springboot.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class DiagnosisDto extends BasicRowInfo implements Serializable {

    private static final long serialVersionUID = -8115212120508315367L;

    private String id;

    private String description;

    private String status;

    private UserDto userDto;

    private PatientDto patientDto;

    private AppointmentDto appointmentDto;

    private List<DrugProfileDto> drugProfileDtos;

    private Timestamp timestamp;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<DrugProfileDto> getDrugProfileDtos() {
        return drugProfileDtos;
    }

    public void setDrugProfileDtos(List<DrugProfileDto> drugProfileDtos) {
        this.drugProfileDtos = drugProfileDtos;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
