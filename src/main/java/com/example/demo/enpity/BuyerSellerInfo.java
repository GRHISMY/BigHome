package com.example.demo.enpity;

import java.io.Serializable;
import java.util.Date;

public class BuyerSellerInfo implements Serializable {
    private Integer bSId;
    private String bSName;
    private String nickName;
    private Integer sex;
    private String icon;
    private String telephone;
    private String pwd;
    private String home;
    private String home_detail;
//    1表示买家，0表示卖家
    private  Integer status;
//    1表示存在，0表示销户，一般来说，不能真正删除数据。删除之后，不管是从管理还是法律，都是不方便的。
    private Integer bSStatus;
    private String email;
    private Date lastLoginTime;
    private Date registerTime;

    public BuyerSellerInfo() {
    }

    @Override
    public String toString() {
        return "BuyerSellerInfo{" +
                "bSId=" + bSId +
                ", bSName='" + bSName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", icon='" + icon + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", home='" + home + '\'' +
                ", home_detail='" + home_detail + '\'' +
                ", status=" + status +
                ", bSStatus=" + bSStatus +
                ", email='" + email + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", registerTime=" + registerTime +
                '}';
    }

    public Integer getbSId() {
        return bSId;
    }

    public void setbSId(Integer bSId) {
        this.bSId = bSId;
    }

    public String getbSName() {
        return bSName;
    }

    public void setbSName(String bSName) {
        this.bSName = bSName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHome_detail() {
        return home_detail;
    }

    public void setHome_detail(String home_detail) {
        this.home_detail = home_detail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getbSStatus() {
        return bSStatus;
    }

    public void setbSStatus(Integer bSStatus) {
        this.bSStatus = bSStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}
