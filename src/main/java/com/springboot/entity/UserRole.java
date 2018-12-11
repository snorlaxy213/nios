package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "User_Role",catalog = "",schema = "")
public class UserRole implements Serializable {

    private static final Long serialVersionUID = 3L;

    @Id
    @Column(name = "UserRole_ID", length = 12)
    private String id;

    @Column(name = "UserRole_Name", length = 30, nullable = false)
    private String name;

    @Column(name = "UserRole_Desc", length = 255, nullable = false)
    private String description;

    @Column(name = "Status", length = 1, nullable = false)
    private String status;

    @Embedded
    private BasicInfomation basicInfomation;

    @Version
    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @ManyToMany(
            mappedBy = "userroles",
            targetEntity = User.class,
            fetch = FetchType.LAZY
    )
    private List<User> users;

    @ManyToMany(
            targetEntity = Permission.class,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "User_Role_Permission",
            joinColumns = @JoinColumn(name = "Userrole_ID"),
            inverseJoinColumns = @JoinColumn(name = "Permission_ID")
    )
    private List<Permission> permissions;


    public UserRole() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
