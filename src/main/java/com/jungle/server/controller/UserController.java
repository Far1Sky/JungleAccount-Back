package com.jungle.server.controller;

import com.jungle.pojo.User;
import com.jungle.server.mapper.UserMapper;
import com.jungle.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin // 解决跨域问题
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserMapper userMapper;

//    QueryWrapper<User> wrapper = new QueryWrapper<>();

    @Autowired
    UserService userService;

    // 模拟自定义情况
    @RequestMapping("/select2")
    public User select2(){
        return userMapper.select2();
    }

//    @PostMapping("/register")
//    public Result register(String username, String password){
//        //查询用户
//        User u = userService.findByUserName(username);
//        if (u == null) {
//            userMapper.selectList(wrapper);
//            //没有占用
//            //注册
//            userService.register(username, password);
//            return Result.success();
//        } else {
//            //占用
//            return Result.error("用户名已被占用");
//        }
//
//    }

    @PostMapping
    public void saveUser(User user){
        userService.save(user);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(Long id){
        userService.removeById(id);
    }

    @GetMapping("{id}")
    public void queryUserById(Long id){
        User user = userService.getById(id);
    }
}
