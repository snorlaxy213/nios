package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "drug_stock")
public class DrugStock implements Serializable {

    private static final long serialVersionUID = 1726372240170772847L;

    String id;

    int amount;

    DrugProfile drugProfile;

    Diagnosis diagnosis;

    private BasicInformation basicInformation;

    private Timestamp timestamp;

    public DrugStock() {
    }

    @Id
    @Column(name = "stock_id", length = 12)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "amount", length = 6)
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @OneToOne
    @JoinColumn(name = "drug_id")
    public DrugProfile getDrugProfile() {
        return drugProfile;
    }

    public void setDrugProfile(DrugProfile drugProfile) {
        this.drugProfile = drugProfile;
    }

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    @Basic(fetch = FetchType.LAZY)
    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public BasicInformation getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(BasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
