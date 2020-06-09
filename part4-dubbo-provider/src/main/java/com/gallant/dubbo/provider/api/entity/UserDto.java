package com.gallant.dubbo.provider.api.entity;


import lombok.Data;

@Data
public class UserDto {
    private int userid;
    private String username;
    private String address;
}