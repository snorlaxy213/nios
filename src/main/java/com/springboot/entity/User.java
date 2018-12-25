package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User_Profile",catalog = "nios",schema = "")
public class User implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "User_ID", length = 12)
    private String id;

    @Column(name = "Name",length = 100, nullable = false)
    private String name;

    @Column(name = "Email",length = 50, nullable = false)
    private String email;

    @Column(name = "Mobile",length = 100)
    private String mobile;

    @Column(name = "Password_Hash",length = 200,nullable = false)
    private String password;

    @Column(name = "Effective_DTM",nullable = false)
    private Date effective;

    @Column(name = "Expiry_DTM")
    private Date expiry;

    @Column(name = "Change_Password_DTM")
    private Date changePassword;

    @Column(name = "Last_Success_Login_DTM")
    private Date lastSuccessLogin;

    @Column(name = "last_Failed_Login_DTM")
    private Date lastFailedLogin;

    @Column(name = "Failed_Login_Count")
    private Integer failedLoginCount;

    @Embedded
    private BasicInfomation basicInfomation;

    @Version
    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @ManyToMany(targetEntity = UserRole.class, fetch = FetchType.LAZY)
    @JoinTable(name = "User_User_Role",
            joinColumns = @JoinColumn(name = "User_ID"),
            inverseJoinColumns = @JoinColumn(name = "Userrole_ID"))
    private List<UserRole> userroles;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getUserroles() {
        return userroles;
    }

    public void setUserroles(List<UserRole> userroles) {
        this.userroles = userroles;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getEffective() {
        return effective;
    }

    public void setEffective(Date effective) {
        this.effective = effective;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public Date getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(Date changePassword) {
        this.changePassword = changePassword;
    }

    public Date getLastSuccessLogin() {
        return lastSuccessLogin;
    }

    public void setLastSuccessLogin(Date lastSuccessLogin) {
        this.lastSuccessLogin = lastSuccessLogin;
    }

    public Date getLastFailedLogin() {
        return lastFailedLogin;
    }

    public void setLastFailedLogin(Date lastFailedLogin) {
        this.lastFailedLogin = lastFailedLogin;
    }

    public Integer getFailedLoginCount() {
        return failedLoginCount;
    }

    public void setFailedLoginCount(Integer failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }

    public BasicInfomation getBasicInfomation() {
        return basicInfomation;
    }

    public void setBasicInfomation(BasicInfomation basicInfomation) {
        this.basicInfomation = basicInfomation;
    }
}
