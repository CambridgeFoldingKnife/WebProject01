package com.zut.controller;

import com.zut.pojo.PagBean;
import com.zut.pojo.Result;
import com.zut.pojo.Student;
import com.zut.pojo.StudentQueryParam;
import com.zut.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    @Autowired
  private StudentService studentService;
  @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
      log.info("分页查询：{}",studentQueryParam);
      PagBean pagBean = studentService.page(studentQueryParam);
      return Result.success(pagBean);
  }
//  查询参数可用此法 但接口中时路径参数
 /* @DeleteMapping
  public Result delete(@RequestParam List<Integer> ids){
      log.info("批量删除：{}",ids);
      studentService.delete(ids);
      return Result.success();
  }*/
//    路径参数id批量删除
 @DeleteMapping("/{ids}")
 public Result delete(@PathVariable String ids) {
     // 解析路径中的逗号分隔ID，转为List<Integer>
     List<Integer> idList = Arrays.stream(ids.split(","))
             .map(Integer::parseInt)
             .collect(Collectors.toList());
     log.info("批量删除：{}", idList);
     studentService.delete(idList);
     return Result.success();
 }
 //添加学员

    @PostMapping()
    public Result add(@RequestBody Student student){
        log.info("添加学员：{}",student);
        studentService.add(student);
        return Result.success();
    }
    //根据id查询学员详细信息--页面回显用于下一步修改操作
 @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询回显，id：{}",id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }
    //更新学员信息
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("根据id修改学员{}",student);
        studentService.update(student);
        return Result.success();
    }
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id,
                                  @PathVariable Integer score){
        log.info("根据id修改学员{}",id);
        studentService.updateViolation(id,score);
        return Result.success();
    }

}
