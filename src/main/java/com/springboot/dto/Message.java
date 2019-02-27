package com.springboot.dto;

import java.util.HashMap;
import java.util.Map;

public class Message {
    // 100-success，200-fail
    private Integer code;

    private String message;

    private Map<String, Object> content = new HashMap<String, Object>();

    public static Message success() {
        Message result = new Message();
        result.setCode(100);
        return result;
    }

    public static Message success(String msg) {
        Message result = new Message();
        result.setCode(100);
        result.setMessage(msg);
        return result;
    }

    public static Message success(String msg, Map<String, Object> content) {
        Message result = new Message();
        result.setMessage(msg);
        result.setContent(content);
        return result;
    }

    public static Message success(int code, String msg, Map<String, Object> content) {
        Message result = new Message();
        result.setCode(code);
        result.setMessage(msg);
        result.setContent(content);
        return result;
    }

    public static Message fail(String msg) {
        Message result = new Message();
        result.setCode(200);
        result.setMessage(msg);
        return result;
    }

    public static Message fail(int code, String msg) {
        Message result = new Message();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public Message add(String key, Object value) {
        this.getContent().put(key, value);
        return this;
    }
}
