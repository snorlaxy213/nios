package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "User_Profile")
public class User implements Serializable {

    private static final long serialVersionUID = 5234620528341129687L;

    private String id;

    private String name;

    private String email;

    private String mobile;

    private String password;

    private String office;

    private Integer orderNum;

    private Integer currentNum;

    private BasicInformation basicInformation;

    private Timestamp timestamp;

    private List<UserRole> userRoles;

    private List<Appointment> appointments;

    private List<Diagnosis> diagnoses;

    public User() {
    }

    @Id
    @Column(name = "User_ID", length = 12)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "Name", length = 100, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Email", length = 50, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Mobile", length = 100)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "Password_Hash", length = 200, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "office", length = 200, nullable = false)
    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Column(name = "orderNum", length = 200)
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Column(name = "currentNum", length = 200)
    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
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

    @ManyToMany(targetEntity = UserRole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "User_User_Role",    //用来指定中间表的名称
            joinColumns = @JoinColumn(name = "User_ID"),    //用于指定本表在中间表的字段名称，以及中间表依赖的是本表的哪个字段
            inverseJoinColumns = @JoinColumn(name = "UserRole_ID"))    //用于指定对方表在中间表的字段名称，以及中间表依赖的是它的哪个字段
    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }
}
