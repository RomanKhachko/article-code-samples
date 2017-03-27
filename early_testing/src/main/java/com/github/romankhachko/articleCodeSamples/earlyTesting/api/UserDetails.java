package com.github.romankhachko.articleCodeSamples.earlyTesting.api;

/**
 * Created by roman on 3/25/17.
 */
public class UserDetails {
    private String userName;
    private String userRole;
    private String userStatus;
    private Long userID;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
