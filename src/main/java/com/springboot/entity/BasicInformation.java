package com.springboot.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class BasicInformation implements Serializable {

    private static final long serialVersionUID = -5822147338970168191L;

    String createBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date createDtm;

    private Integer createClinic;

    String updateBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date updateDtm;

    private Integer updateClinic;

    public BasicInformation() {
    }

    @Column(name = "Create_By",length = 100,nullable = false,updatable = false)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "Create_Dtm",nullable = false,updatable = false)
    public Date getCreateDtm() {
        return createDtm;
    }

    public void setCreateDtm(Date createDtm) {
        this.createDtm = createDtm;
    }

    @Column(name = "Create_Clinic",length = 1,nullable = false,updatable = false)
    public Integer getCreateClinic() {
        return createClinic;
    }

    public void setCreateClinic(Integer createClinic) {
        this.createClinic = createClinic;
    }

    @Column(name = "Update_By",length = 100, nullable = false)
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name = "Update_Dtm",nullable = false)
    public Date getUpdateDtm() {
        return updateDtm;
    }

    public void setUpdateDtm(Date updateDtm) {
        this.updateDtm = updateDtm;
    }

    @Column(name = "Update_Clinic",length = 1,nullable = false)
    public Integer getUpdateClinic() {
        return updateClinic;
    }

    public void setUpdateClinic(Integer updateClinic) {
        this.updateClinic = updateClinic;
    }
}
