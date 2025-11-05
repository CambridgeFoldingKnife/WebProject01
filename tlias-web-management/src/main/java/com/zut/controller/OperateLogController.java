package com.zut.controller;

import com.zut.pojo.PagBean;
import com.zut.pojo.Result; // 导入Result类
import com.zut.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class OperateLogController {
    @Autowired
    private OperateLogService operateLogService;

    @GetMapping("/page")
    public Result getLogPage( // 返回值改为Result
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        PagBean pagBean = operateLogService.getLogPage(page, pageSize);
        return Result.success(pagBean); // 用Result封装PagBean，对应文档的data字段
    }
}