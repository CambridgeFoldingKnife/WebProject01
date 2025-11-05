package com.zut.controller;

import com.zut.anno.Log;
import com.zut.pojo.Clazz;
import com.zut.pojo.ClazzQueryParam;
import com.zut.pojo.PagBean;
import com.zut.pojo.Result;
import com.zut.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clazzs")
@Slf4j
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
//    班级列表分页查询显示
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("班级分页查询：{}",clazzQueryParam);
        PagBean pagBean = clazzService.page(clazzQueryParam);
        return Result.success(pagBean);
    }
//根据班级id删除班级
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
       log.info("以id删除部门：id={}",id);
       clazzService.deleteById(id);
       return Result.success();
    }
//新增员工 对象接收参数加注解requestBody
    @Log
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("新增班级：{}",clazz);
        clazzService.add(clazz);
        return Result.success();

    }
//   根据id查询班级详细信息--页面回显用于下一步修改操作
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询回显，id：{}",id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }

//更新班级信息
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("根据id修改班级{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    //查询所有班级集合
@GetMapping("/list")
    public Result getClazzList(){
    List<Clazz> clazzList = clazzService.getClazzList();
    return Result.success(clazzList);
}

}
