package com.springboot.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DispensingDrugDto implements Serializable {

    private static final long serialVersionUID = -9062644894193560235L;

    private String drugName;

    private int amount;

    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
