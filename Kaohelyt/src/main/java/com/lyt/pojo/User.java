package com.lyt.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 8174034842704209729L;
    private int UserId;
    private String UserName;
    private String UserPassword;
    private String UserFace;
    private String UserLoginName;

    public int getUserId() {
        return UserId;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                ", UserPassword='" + UserPassword + '\'' +
                ", UserFace='" + UserFace + '\'' +
                ", UserLoginName='" + UserLoginName + '\'' +
                ", UserJ='" + UserJ + '\'' +
                ", UserEmail='" + UserEmail + '\'' +
                ", G='" + G + '\'' +
                ", F='" + F + '\'' +
                ", K='" + K + '\'' +
                '}';
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserFace() {
        return UserFace;
    }

    public void setUserFace(String userFace) {
        UserFace = userFace;
    }

    public String getUserLoginName() {
        return UserLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        UserLoginName = userLoginName;
    }

    public String getUserJ() {
        return UserJ;
    }

    public void setUserJ(String userJ) {
        UserJ = userJ;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getG() {
        return G;
    }

    public void setG(String g) {
        G = g;
    }

    public String getF() {
        return F;
    }

    public void setF(String f) {
        F = f;
    }

    public String getK() {
        return K;
    }

    public void setK(String k) {
        K = k;
    }

    private String UserJ;
    private String UserEmail;
    private String G;
    private String F;
    private String K;

    public User(int userId, String userName, String userPassword, String userFace, String userLoginName, String userJ, String userEmail, String g, String f, String k) {
        this.UserId = userId;
        this.UserName = userName;
        this.UserPassword = userPassword;
        this.UserFace = userFace;
        this.UserLoginName = userLoginName;
        this.UserJ = userJ;
        this.UserEmail = userEmail;
        this.G = g;
        this.F = f;
        this.K = k;
    }
}
