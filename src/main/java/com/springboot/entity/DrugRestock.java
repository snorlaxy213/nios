package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "Drug_Restock")
public class DrugRestock implements Serializable {

    private static final long serialVersionUID = 7057426805065006955L;

    String id;

    int amount;

    Date expiry;

    String remark;

    DrugProfile drugProfile;

    private BasicInformation basicInformation;

    private Timestamp timestamp;

    public DrugRestock() {
    }

    @Id
    @Column(name = "restock_id",length = 12)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "amount", length = 10, nullable = false)
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column(name = "expiry", nullable = false)
    public Date getExpiry() {
        return expiry;
    }

    public String getRemark() {
        return remark;
    }

    @Column(name = "remark", length = 500, nullable = false)
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    @OneToOne(targetEntity = DrugProfile.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_id")
    public DrugProfile getDrugProfile() {
        return drugProfile;
    }

    public void setDrugProfile(DrugProfile drugProfile) {
        this.drugProfile = drugProfile;
    }




    @Embedded
    public BasicInformation getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(BasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    @Version
    @Column(name = "Timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
