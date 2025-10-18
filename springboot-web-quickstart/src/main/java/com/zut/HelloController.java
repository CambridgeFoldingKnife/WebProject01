package com.zut;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//说明这是个请求处理类
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(String name ){
        System.out.println("name: "+name);
        return "Hello "+name+" ~ ";
    }
}
