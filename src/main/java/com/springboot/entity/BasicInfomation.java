package com.springboot.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class BasicInfomation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "Create_By",length = 100,nullable = false,updatable = false)
    String createBy;

    @Column(name = "Create_Dtm",nullable = false,updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date createDtm;

    @Column(name = "Create_Clinic",length = 1,nullable = false,updatable = false)
    private Integer createClinic;

    @Column(name = "Update_By",length = 100, nullable = true)
    String updateBy;

    @Column(name = "Update_Dtm",nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date updateDtm;

    @Column(name = "Update_Clinic",length = 1,nullable = true)
    private Integer updateClinic;

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

    public Integer getCreateClinic() {
        return createClinic;
    }

    public void setCreateClinic(Integer createClinic) {
        this.createClinic = createClinic;
    }

    public Integer getUpdateClinic() {
        return updateClinic;
    }

    public void setUpdateClinic(Integer updateClinic) {
        this.updateClinic = updateClinic;
    }
}
