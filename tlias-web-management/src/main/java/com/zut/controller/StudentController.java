package com.zut.controller;

import com.zut.pojo.PagBean;
import com.zut.pojo.Result;
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
 /* @DeleteMapping
  public Result delete(@RequestParam List<Integer> ids){
      log.info("批量删除：{}",ids);
      studentService.delete(ids);
      return Result.success();
  }*/
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
}
