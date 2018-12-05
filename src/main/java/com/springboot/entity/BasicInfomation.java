package com.springboot.entity;

import java.util.Date;

public class BasicInfomation {
    String createBy;
    Date createDtm;
    String updateBy;
    Date updateDtm;

    public String getCreateBy() {
        return createBy;
    }

    public Date getCreateDtm() {
        return createDtm;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public Date getUpdateDtm() {
        return updateDtm;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreateDtm(Date createDtm) {
        this.createDtm = createDtm;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateDtm(Date updateDtm) {
        this.updateDtm = updateDtm;
    }
}
