package com.springboot.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Parameter")
public class Parameter implements Serializable {

    private static final long serialVersionUID = 3311592962431437702L;

    String Parameter;

    Integer Value;

    Timestamp timestamp;

    public Parameter() {
    }

    @Id
    @Column(name = "parameter", length = 100)
    public String getParameter() {
        return Parameter;
    }

    public void setParameter(String parameter) {
        Parameter = parameter;
    }

    @Column(name = "value")
    public Integer getValue() {
        return Value;
    }

    public void setValue(Integer value) {
        Value = value;
    }

    @Version
    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
