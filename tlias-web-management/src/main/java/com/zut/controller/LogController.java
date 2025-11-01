package com.zut.controller;

import com.zut.pojo.PagBean;
import com.zut.pojo.Result;
import com.zut.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/log")
public class LogController {
    @Autowired
private LogService logService;
    //日志列表
//该url为请求参数：页码，每页记录数
@GetMapping("/page")
public Result getLogPage(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "10") Integer pageSize
) {
    PagBean pagBean = logService.getLogPage(page, pageSize);
    return Result.success(pagBean);
}
}
