package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "Method")
public class Method implements Serializable {

    private static final long serialVersionUID = 825204172962081252L;

    @Id
    @Column(name = "Method_ID", length = 10)
    private String id;

    @Column(name = "Method_Name", nullable = false)
    private String name;

    @Column(name = "Description", length = 100, nullable = true)
    private String desc;

    @Embedded
    private BasicInfomation basicInfomation;

    @Version
    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @ManyToMany(
            targetEntity = Permission.class,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "Permission_Method",
            joinColumns = @JoinColumn(name = "Method_ID"),
            inverseJoinColumns = @JoinColumn(name = "Permission_ID")
    )
    private Collection<Permission> permissions;

    public Method() {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Collection<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<Permission> permissions) {
        this.permissions = permissions;
    }
}
