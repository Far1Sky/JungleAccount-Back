package com.jungle.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jungle.common.constant.MessageConstant;
import com.jungle.common.constant.StatusConstant;
import com.jungle.common.exception.*;
import com.jungle.pojo.entity.User;
import com.jungle.pojo.dto.UserLoginDTO;
import com.jungle.pojo.dto.UserRegisterDTO;
import com.jungle.server.mapper.UserMapper;
import com.jungle.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Wrapper;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(UserLoginDTO userLoginDTO){
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        // 根据用户名查询数据库中数据 mp方法
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        // 账号不存在
        if(user == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        // 密码错误
        // md5加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(user.getPassword())){
            throw new RegisterException(MessageConstant.PASSWORD_ERROR);
        }
//        // 账号被锁定
//        if(user.getStatus() == StatusConstant.DISABLE){
//            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
//        }
        return user;
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        String username = userRegisterDTO.getUsername();
        String password = userRegisterDTO.getPassword();
        String repeat_password = userRegisterDTO.getRepeat_password();
        // 该用户名已被注册
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if(user != null){
            throw new RegisterException(MessageConstant.ACCOUNT_HAS_BEEN_REGISTERED);
        }
        // 密码不一致
        if(!password.equals(repeat_password)){
            throw new RegisterException(MessageConstant.PASSWORD_INCONSISTENT);
        }
        // md5加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 填入数据库
        User new_user = new User();
        new_user.setUsername(username);
        new_user.setPassword(password);
        userMapper.insert(new_user);
    }
}
