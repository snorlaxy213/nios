package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class BasicInformation implements Serializable {

    private static final long serialVersionUID = -5822147338970168191L;

    String createBy;

    String updateBy;

    Date createDtm;

    Date updateDtm;

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

}
