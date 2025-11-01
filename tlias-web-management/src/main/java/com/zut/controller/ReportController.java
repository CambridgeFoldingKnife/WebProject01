package com.zut.controller;

import com.zut.pojo.JobOption;
import com.zut.pojo.Result;
import com.zut.pojo.StuCountOption;
import com.zut.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
//信息统计
@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;
    //员工职位统计
    @GetMapping("/empJobData")
    public Result empJobData(){
      log.info("统计员工职位人数");
      JobOption jobOption= reportService.empJobData();
      return Result.success(jobOption);
    }

//员工性别统计
    @GetMapping("/empGenderData")
    public Result empGenderData (){
        log.info("统计员工性别人数");
       List<Map> dataList = reportService.empGenderData();
       return Result.success(dataList);
    }
 //学员学历统计
@GetMapping("/studentDegreeData")
    public Result studentDegreeData(){
        log.info("统计学员学历人数");
        List<Map> dataList = reportService.studentDegreeData();
        return Result.success(dataList);
    }

 //班级人数统计
    @GetMapping("/studentCountData")
    public Result studentCountData(){
        log.info("统计班级人数");
        StuCountOption stuCountOption = reportService.studentCountData();
        return Result.success(stuCountOption);
    }

}
