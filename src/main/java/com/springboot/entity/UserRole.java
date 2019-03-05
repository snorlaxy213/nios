package com.springboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "User_Role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -1151816668229028036L;

    private String id;

    private String name;

    private String status;

    private BasicInformation basicInformation;

    private Timestamp timestamp;

    private List<User> users;

    public UserRole() {
    }

    @Id
    @Column(name = "UserRole_ID", length = 12)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "UserRole_Name", length = 30, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Status", length = 1, nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @JsonBackReference
    @ManyToMany(
            mappedBy = "userRoles",
            targetEntity = User.class,
            fetch = FetchType.EAGER
    )
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
