package com.gallant.dubbo.provider.api.service;

import com.gallant.dubbo.provider.api.entity.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getUserList();
}
