package com.springboot.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class DrugRestockDto extends BasicRowInfo implements Serializable {

    private static final long serialVersionUID = 3257783510382792374L;

    String id;

    int amount;

    Date expiry;

    String remark;

    DrugProfileDto drugProfileDto;

    private Timestamp timestamp;

    public DrugRestockDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public DrugProfileDto getDrugProfileDto() {
        return drugProfileDto;
    }

    public void setDrugProfileDto(DrugProfileDto drugProfileDto) {
        this.drugProfileDto = drugProfileDto;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
