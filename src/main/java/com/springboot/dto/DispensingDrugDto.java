package com.springboot.dto;

import java.io.Serializable;

public class DispensingDrugDto implements Serializable {

    private static final long serialVersionUID = -9062644894193560235L;

    private String drugName;

    private int amount;

    public DispensingDrugDto() {
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
