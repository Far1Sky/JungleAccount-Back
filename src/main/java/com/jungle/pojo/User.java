package com.jungle.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // 自动完成get、set、ToString
@AllArgsConstructor // 所有有参构造方法
@NoArgsConstructor // 所有无参构造方法
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private Integer balance;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
