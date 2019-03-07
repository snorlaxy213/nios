package com.springboot.exception;

import java.util.List;

public class GlobalException extends RuntimeException  {

    private String msgCode;
    private String content;
    private String errorMessage;
    private List<ErrorField> errorFields;

    public GlobalException() {
    }

    public GlobalException(String msgCode, String content) {
        this.msgCode = msgCode;
        this.content = content;
    }

    public GlobalException(String msgCode, String content, String errorMessage) {
        this.msgCode = msgCode;
        this.content = content;
        this.errorMessage = errorMessage;
    }

    public GlobalException(String msgCode, String content, List<ErrorField> errorFields) {
        this.msgCode = msgCode;
        this.content = content;
        this.errorFields = errorFields;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ErrorField> getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(List<ErrorField> errorFields) {
        this.errorFields = errorFields;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
