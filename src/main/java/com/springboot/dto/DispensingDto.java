package com.springboot.dto;

import java.io.Serializable;
import java.util.List;

public class DispensingDto implements Serializable {

    private static final long serialVersionUID = -4674268438631364650L;

    private List<DispensingDrugDto> dispensingDrugDtos;

    private String description;

    private String userName;

    private String patientName;

    public DispensingDto() {
    }

    public List<DispensingDrugDto> getDispensingDrugDtos() {
        return dispensingDrugDtos;
    }

    public void setDispensingDrugDtos(List<DispensingDrugDto> dispensingDrugDtos) {
        this.dispensingDrugDtos = dispensingDrugDtos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
