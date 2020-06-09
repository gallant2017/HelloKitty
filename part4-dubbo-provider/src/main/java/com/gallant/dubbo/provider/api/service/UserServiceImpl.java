package com.gallant.dubbo.provider.api.service;

import com.gallant.dubbo.provider.api.entity.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl {

    public List<UserDto> getUserList() {
        List<UserDto> list = new ArrayList<>();
        UserDto user = new UserDto();
        user.setUserid(1);
        user.setUsername("zhangsan");
        user.setAddress("电子大厦");
        list.add(user);
        return list;
    }
}
