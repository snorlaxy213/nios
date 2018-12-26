package com.springboot.dto;

import java.util.Date;

public class BasicRowInfo {
    private static final long serialVersionUID = 456L;

    String createBy;

    Date createDtm;

    private Integer createClinic;

    String updateBy;

    Date updateDtm;

    private Integer updateClinic;

    public BasicRowInfo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDtm() {
        return createDtm;
    }

    public void setCreateDtm(Date createDtm) {
        this.createDtm = createDtm;
    }

    public Integer getCreateClinic() {
        return createClinic;
    }

    public void setCreateClinic(Integer createClinic) {
        this.createClinic = createClinic;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDtm() {
        return updateDtm;
    }

    public void setUpdateDtm(Date updateDtm) {
        this.updateDtm = updateDtm;
    }

    public Integer getUpdateClinic() {
        return updateClinic;
    }

    public void setUpdateClinic(Integer updateClinic) {
        this.updateClinic = updateClinic;
    }
}
