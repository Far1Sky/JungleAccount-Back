package com.jungle.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jungle.pojo.entity.User;
import com.jungle.pojo.dto.UserLoginDTO;
import com.jungle.pojo.dto.UserRegisterDTO;


public interface UserService extends IService<User> {
    void register(UserRegisterDTO userRegisterDTO);
    User login(UserLoginDTO userLoginDTO);
}
