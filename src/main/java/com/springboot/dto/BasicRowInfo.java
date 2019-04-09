package com.springboot.dto;

import java.io.Serializable;
import java.util.Date;

public class BasicRowInfo implements Serializable {

    private static final long serialVersionUID = -2912625519140191986L;

    private String createBy;

    private Date createDtm;

    private String updateBy;

    private Date updateDtm;

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
}
