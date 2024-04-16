package com.jungle.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jungle.pojo.User;
import org.apache.ibatis.annotations.Select;


// mybatis plus的BaseMapper实现了增删改查
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where name like 'zbj';")
    User select2();


    User findByUserName(String username);

    void add(String username, String md5String);
}
