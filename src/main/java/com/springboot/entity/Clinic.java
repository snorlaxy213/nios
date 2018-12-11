package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Clinic")
public class Clinic implements Serializable {

    private static final long serialVersionUID = -3963036394450472808L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Clinic_Code", length = 1)
    private Integer clinicCode;

    @Column(name = "Name", length = 100, nullable = false)
    private String name;

    @Column(name = "Name_CHI", length = 50)
    private String nameChi;

    @Column(name = "Short_Name", length = 20, nullable = false)
    private String shortName;

    @Column(name = "Short_Name_CHI", length = 10)
    private String shortNameChi;

    @Column(name = "Address", length = 100)
    private String address;

    @Column(name = "Address_CHI", length = 100)
    private String addressChi;

    @Column(name = "Phone", length = 60)
    private String phone;

    @Column(name = "Fax", length = 60)
    private String fax;

    @Column(name = "Effective_DTM", nullable = false)
    private Date effectiveDtm;

    @Column(name = "Expiry_DTM")
    private Date expiryDtm;

    @Lob
    @Column(name = "Logo", length = 16777216)
    private byte[] logo;

    @Column(name = "Status", length = 1, nullable = false)
    private String recordStatus;

    @Version
    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @Column(name = "hci_id", nullable = true, length = 10 )
    private String hciID;

    @Column(name = "hci_full_name", nullable = true, length = 255)
    private String hciFullName;

    @Column(name = "is_cms", nullable = true, length = 1)
    private String isCms;

    @Column(name = "is_cmis", nullable = true, length = 1)
    private String isCmis;

    @Embedded
    private BasicInfomation basicInfomation;

    public Clinic() {
    }

    public Integer getClinicCode() {
        return clinicCode;
    }

    public void setClinicCode(Integer clinicCode) {
        this.clinicCode = clinicCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameChi() {
        return nameChi;
    }

    public void setNameChi(String nameChi) {
        this.nameChi = nameChi;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortNameChi() {
        return shortNameChi;
    }

    public void setShortNameChi(String shortNameChi) {
        this.shortNameChi = shortNameChi;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressChi() {
        return addressChi;
    }

    public void setAddressChi(String addressChi) {
        this.addressChi = addressChi;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Date getEffectiveDtm() {
        return effectiveDtm;
    }

    public void setEffectiveDtm(Date effectiveDtm) {
        this.effectiveDtm = effectiveDtm;
    }

    public Date getExpiryDtm() {
        return expiryDtm;
    }

    public void setExpiryDtm(Date expiryDtm) {
        this.expiryDtm = expiryDtm;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getHciID() {
        return hciID;
    }

    public void setHciID(String hciID) {
        this.hciID = hciID;
    }

    public String getHciFullName() {
        return hciFullName;
    }

    public void setHciFullName(String hciFullName) {
        this.hciFullName = hciFullName;
    }

    public String getIsCms() {
        return isCms;
    }

    public void setIsCms(String isCms) {
        this.isCms = isCms;
    }

    public String getIsCmis() {
        return isCmis;
    }

    public void setIsCmis(String isCmis) {
        this.isCmis = isCmis;
    }

    public BasicInfomation getBasicInfomation() {
        return basicInfomation;
    }

    public void setBasicInfomation(BasicInfomation basicInfomation) {
        this.basicInfomation = basicInfomation;
    }
}
