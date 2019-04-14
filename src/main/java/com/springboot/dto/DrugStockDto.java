package com.springboot.dto;

import java.io.Serializable;

public class DrugStockDto extends BasicRowInfo implements Serializable {

    private static final long serialVersionUID = -3609385813772445403L;

    String id;

    int amount;

    DrugProfileDto drugProfileDto;

    DiagnosisDto diagnosisDto;

    public DrugStockDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public DrugProfileDto getDrugProfileDto() {
        return drugProfileDto;
    }

    public void setDrugProfileDto(DrugProfileDto drugProfileDto) {
        this.drugProfileDto = drugProfileDto;
    }

    public DiagnosisDto getDiagnosisDto() {
        return diagnosisDto;
    }

    public void setDiagnosisDto(DiagnosisDto diagnosisDto) {
        this.diagnosisDto = diagnosisDto;
    }
}
