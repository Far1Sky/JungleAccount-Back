package com.jungle.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // 自动完成get、set、ToString
@AllArgsConstructor // 所有有参构造方法
@NoArgsConstructor // 所有无参构造方法
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
}
