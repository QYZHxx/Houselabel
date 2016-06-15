package com.goobole.houselabel.bean;

public class User {//用户类

    private String userName;//用户名
    private String userPassWd;//用户密码
    private String userGrade;//用户等级
    private String userImage;//用户头像
    private String userCallNumber;//用户电话
    private String userPeriod;//届数
    private String userAcademy;//学院
    private String userSign;//个人签名

    public User() {
    }


    public String getUserPassWd() {
        return userPassWd;
    }

    public void setUserPassWd(String userPassWd) {
        this.userPassWd = userPassWd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserCallNumber() {
        return userCallNumber;
    }

    public void setUserCallNumber(String userCallNumber) {
        this.userCallNumber = userCallNumber;
    }

    public String getUserPeriod() {
        return userPeriod;
    }

    public void setUserPeriod(String userPeriod) {
        this.userPeriod = userPeriod;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUserAcademy() {
        return userAcademy;
    }

    public void setUserAcademy(String userAcademy) {
        this.userAcademy = userAcademy;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
