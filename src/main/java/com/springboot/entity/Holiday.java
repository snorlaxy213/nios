package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="Holiday")
public class Holiday implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @Column(name = "Holiday_Dtm")
    private Date dtm;

    @Column(name = "Holiday_Desc", length = 100, nullable = false)
    private String desc;

    @Column(name = "Holiday_Desc_CHI", length = 100)
    private String descChi;

    @Embedded
    private BasicInfomation basicInfomation;

    @Version
    @Column(name = "Timestamp")
    private Timestamp timestamp;

    public Holiday() {
    }

    public Date getDtm() {
        return dtm;
    }

    public void setDtm(Date dtm) {
        this.dtm = dtm;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescChi() {
        return descChi;
    }

    public void setDescChi(String descChi) {
        this.descChi = descChi;
    }

    public BasicInfomation getBasicInfomation() {
        return basicInfomation;
    }

    public void setBasicInfomation(BasicInfomation basicInfomation) {
        this.basicInfomation = basicInfomation;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
