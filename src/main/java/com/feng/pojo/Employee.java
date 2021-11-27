package com.feng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id; // 主键
    private String username; // 姓名
    private String password; // 密码
    private int gender; // 性别
    private String mobile; // 手机号
    private Date birthday; // 出生日期
    private Date created_time; // 创建时间
}
