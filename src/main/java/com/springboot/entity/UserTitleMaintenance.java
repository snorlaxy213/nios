package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "User_Title_Maintenance")
public class UserTitleMaintenance implements Serializable {

    private static final long serialVersionUID = 6456L;

    @Id
    @Column(name = "Title_id", length = 12)
    private String titleId;

    @Column(name="Title", length = 255)
    private String title;

    @Column(name="Title_chi", length = 255)
    private String titleChi;

    @Column(name="Status", length = 1)
    private String status;

    @Version
    @Column(name = "TSMP")
    private Timestamp tsmp;

    @Embedded
    private BasicInfomation basicInfomation;

    public UserTitleMaintenance() {
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleChi() {
        return titleChi;
    }

    public void setTitleChi(String titleChi) {
        this.titleChi = titleChi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTsmp() {
        return tsmp;
    }

    public void setTsmp(Timestamp tsmp) {
        this.tsmp = tsmp;
    }

    public BasicInfomation getBasicInfomation() {
        return basicInfomation;
    }

    public void setBasicInfomation(BasicInfomation basicInfomation) {
        this.basicInfomation = basicInfomation;
    }
}
