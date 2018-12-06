package com.springboot.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;


public class BasicInfomation {
    @Column(name = "createBy",length = 100,nullable = false)
    String createBy;

    @Column(name = "createDtm",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date createDtm;


    @Column(name = "updateby",length = 100, nullable = false)
    String updateBy;

    @Column(name = "updateDtm",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
