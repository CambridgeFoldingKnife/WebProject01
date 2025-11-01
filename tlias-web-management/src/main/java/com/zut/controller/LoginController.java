package com.zut.controller;

import com.zut.pojo.Emp;
import com.zut.pojo.LoginInfo;
import com.zut.pojo.Result;
import com.zut.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    //员工对象中有用户名密码 员工登录
    public Result login(@RequestBody Emp emp){
      log.info("员工登录....");
        LoginInfo loginInfo = empService.login(emp);
        if(loginInfo != null){
            return Result.success(loginInfo);//
        }
         return Result.error("用户名或密码错误");
    }


}
