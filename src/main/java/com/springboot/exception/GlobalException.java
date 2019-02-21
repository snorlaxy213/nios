package com.springboot.exception;

public class GlobalException extends RuntimeException  {
    private static final long serialVersionUID = 6217595989138800316L;

    String code;
    String message;

    public GlobalException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
