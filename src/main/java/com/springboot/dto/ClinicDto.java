package com.springboot.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ClinicDto extends BasicRowInfo implements Serializable {

    private static final long serialVersionUID = -6813686098057186757L;

    private Integer clinicCode;

    private String name;

    private String address;

    private String phone;

    private String recordStatus;

    private Timestamp timestamp;

    public ClinicDto() {
    }

    public ClinicDto(Integer clinicCode, String name, String address, String phone, String recordStatus) {
        this.clinicCode = clinicCode;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.recordStatus = recordStatus;
    }

    public Integer getClinicCode() {
        return clinicCode;
    }

    public void setClinicCode(Integer clinicCode) {
        this.clinicCode = clinicCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
