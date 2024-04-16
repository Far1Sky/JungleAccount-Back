package com.jungle.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jungle.pojo.entity.User;
import org.apache.ibatis.annotations.Select;


// mybatis plus的BaseMapper实现了增删改查
public interface UserMapper extends BaseMapper<User> {

}
