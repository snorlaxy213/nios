package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = -8190381158553087757L;

    @Id
    @Column(name = "Permission_ID")
    private int id;

    @Column(name = "Permission_Name", length = 50, nullable = false)
    private String name;

    @Column(name = "Permission_Desc", nullable = false)
    private String description;

    @Embedded
    private BasicInfomation basicInfomation;

    @Version
    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @ManyToMany(
            mappedBy = "permissions",
            targetEntity = Method.class,
            fetch = FetchType.LAZY
    )
    //@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    private List<Method> methods;

    @ManyToMany(
            mappedBy = "permissions",
            targetEntity = UserRole.class,
            fetch = FetchType.LAZY
    )
    private List<UserRole> userRoles;

    public Permission() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
