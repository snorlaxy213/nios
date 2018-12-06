package com.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Role",catalog = "",schema = "")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "role_name",nullable = false)
    String name;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
