package com.jungle.server.controller;

import com.jungle.common.constant.JwtClaimsConstant;
import com.jungle.common.properties.JwtProperties;
import com.jungle.common.result.Result;
import com.jungle.common.utils.JwtUtil;
import com.jungle.pojo.entity.User;
import com.jungle.pojo.dto.UserLoginDTO;
import com.jungle.pojo.dto.UserRegisterDTO;
import com.jungle.pojo.vo.UserLoginVO;
import com.jungle.server.mapper.UserMapper;
import com.jungle.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin // 解决跨域问题
@RequestMapping("/users")
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    // 注册
    @PostMapping("/register")
    @ApiOperation("员工注册")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO){
        userService.register(userRegisterDTO);
        return Result.success();
    }

    // 登录
    @PostMapping("/login")
    @ApiOperation("员工登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        User user = userService.login(userLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, user.getId());
        // TODO 写token逻辑
//        String token = JwtUtil.createJWT(
//                jwtProperties.getAdminSecretKey(),
//                jwtProperties.getAdminTtl(),
//                claims);
        String token = "zbjforever0";
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }

    // 退出登录
    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public Result<String> logout(){
        return Result.success();
    }



}
