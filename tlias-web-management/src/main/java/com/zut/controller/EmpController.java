package com.zut.controller;

import com.zut.pojo.Emp;
import com.zut.pojo.EmpQueryParam;
import com.zut.pojo.PagBean;
import com.zut.pojo.Result;
import com.zut.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {
     //注入service对象
    @Autowired
    private EmpService empService;
    /*
      1.接受参数
      2.调用service
      3.（返回）响应参数
     */

   /* public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize
                        , String name, Integer gender,
                       @DateTimeFormat(pattern ="yyyy-MM-dd")LocalDate begin,
                       @DateTimeFormat(pattern ="yyyy-MM-dd")LocalDate end) {
        //日志输出
            log.info("分页查询：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
            PagBean pagBean =  empService.page(page,pageSize);
            return Result.success(pagBean);

    }*/
   @GetMapping()
    public Result page(EmpQueryParam queryParam) {
        //日志输出
        log.info("分页查询：{}",queryParam);
        PagBean pagBean = empService.page(queryParam);
        return Result.success(pagBean);

    }

/*
 *  新增员工
 */

    @PostMapping
    public Result add(@RequestBody Emp emp){
         log.info("新增员工：{}",emp);
         empService.add(emp);
         return Result.success();
    }

}
