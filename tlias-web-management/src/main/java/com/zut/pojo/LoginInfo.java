package com.zut.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
//封装登陆结果  根据接口文档
public class LoginInfo {
    private Integer id;
    private String username;//用户名

    private String name;// 姓名
    private String token;//令牌
}
