package com.gallant.spring.cloud.part3springcloudeurekaconsumer.controller;

/**
 * Created by huangjunhao on 20/3/4.
 */
public class UsersDto {

    private String userName;
    private String sex;
    private String addrs;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddrs() {
        return addrs;
    }

    public void setAddrs(String addrs) {
        this.addrs = addrs;
    }
}
