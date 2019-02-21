package com.springboot.dto;

import java.util.HashMap;
import java.util.Map;

public class Message {
    // 状态码100-成功，200-失败
    private int code;
    // 提示信息
    private String msg;

    // 用户要返回给浏览器的JSON数据
    private Map<String, Object> extend = new HashMap<String, Object>();

    public static Message success() {
        Message result = new Message();
        result.setCode(100);
        result.setMsg("success！");
        return result;
    }

    public static Message success(String msg) {
        Message result = new Message();
        result.setCode(100);
        result.setMsg(msg);
        return result;
    }

    public static Message success(int code, String msg) {
        Message result = new Message();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Message fail() {
        Message result = new Message();
        result.setCode(200);
        result.setMsg("fail！");
        return result;
    }

    public static Message fail(int code, String msg) {
        Message result = new Message();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Message fail(String msg) {
        Message result = new Message();
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }

    public Message setData(Map<String, Object> extend) {
        Message result = new Message();
        result.setExtend(extend);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public Message add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }
}
