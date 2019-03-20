package com.springboot.exception;

public class ErrorField {
    private String fieldName;
    private String desc;

    public ErrorField() {
    }

    public ErrorField(String fieldName, String desc) {
        this.fieldName = fieldName;
        this.desc = desc;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
