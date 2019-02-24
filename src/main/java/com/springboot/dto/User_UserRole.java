package com.springboot.dto;

import java.io.Serializable;

public class User_UserRole implements Serializable {

    private static final long serialVersionUID = -4036266047734026797L;

    String userId;
    String userRoleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }
}
