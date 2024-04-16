package com.jungle.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jungle.pojo.User;

public interface UserService extends IService<User> {
    User findByUserName(String username);

    void register(String username, String password);
}
