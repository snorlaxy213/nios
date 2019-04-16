package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "diagnosis")
public class Diagnosis implements Serializable {

    private static final long serialVersionUID = -1750778873743954413L;

    private String id;

    private String description;

    private User user;

    private Patient patient;

    private List<DrugStock> drugStocks;

    private BasicInformation basicInformation;

    private Timestamp timestamp;

    public Diagnosis() {
    }

    @Id
    @Column(name = "diagnosis_id", length = 12)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "description", length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "User_ID")
    @Basic(fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "Patient_Key")
    @Basic(fetch = FetchType.LAZY)
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @OneToMany(mappedBy = "diagnosis", fetch = FetchType.LAZY)
    public List<DrugStock> getDrugStocks() {
        return drugStocks;
    }

    public void setDrugStocks(List<DrugStock> drugStocks) {
        this.drugStocks = drugStocks;
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
