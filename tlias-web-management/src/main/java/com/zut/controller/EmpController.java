package com.zut.controller;

import com.zut.anno.Log;
import com.zut.pojo.Emp;
import com.zut.pojo.EmpQueryParam;
import com.zut.pojo.PagBean;
import com.zut.pojo.Result;
import com.zut.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
//    分页查询员工列表
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
    @Log
    @PostMapping
    public Result add(@RequestBody Emp emp){//前端获取json数据对象
         log.info("新增员工：{}",emp);
         empService.add(emp);
         return Result.success();
    }

/*    //删除员工  接收参数：id数组
@DeleteMapping
    public Result delete(Integer[] dis){
        log.info("批量删除的id为：{}", Arrays.toString(dis));
        return Result.success();
    }*/


//删除员工  接收参数：list集合
    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除的id为：{}",ids);
        empService.delete(ids);
        return Result.success();
    }

//    根据id查询所有员工信息--页面回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询回显，id={}",id);
       Emp emp=  empService.getInfo(id);
       return Result.success(emp);
    }

    //修改员工
    @Log
    @PutMapping
    public  Result update(@RequestBody Emp emp){
        log.info("根据id修改员工{}",emp);
        empService.update(emp);
        return Result.success();
    }

@GetMapping("/list")
    public Result getEmpList(){
        List<Emp> empList = empService.getEmpList();
        return Result.success(empList);
}

}
