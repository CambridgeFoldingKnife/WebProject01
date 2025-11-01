package com.zut.service.impl;

import com.zut.mapper.EmpMapper;
import com.zut.mapper.StudentMapper;
import com.zut.pojo.JobOption;
import com.zut.pojo.StuCountOption;
import com.zut.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportServiceImpl  implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public JobOption empJobData() {
        List<Map> mapList = empMapper.getJobData();
        log.info("mapList:{}", mapList);
         /*
         需要两个集合，
         一个集合存获得职位名称，
         另一个集合需要对应的职位数量
          */
        List<Object> jobList = mapList.stream().map(map -> {
            return map.get("pos");
        }).collect(Collectors.toList());

        List<Object> dataList = mapList.stream().map(map -> {
            return map.get("posCount");
        }).collect(Collectors.toList());

        // 返回jobList
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> empGenderData() {
        return empMapper.getGenderDataMaps();
    }

    //班级管理中统计各学历占比人数
    @Override
    public List<Map> studentDegreeData() {
         log.info("统计学员学历人数");
        List<Map> degreeList = studentMapper.countByDegree();

        return degreeList;
    }

    @Override
    public StuCountOption studentCountData() {
        log.info("统计班级人数");  // 注意：这里日志描述应该是“班级人数”而非“学历人数”，之前的日志写错了
        List<Map> countList = studentMapper.getStuCountData();  // 明确泛型为Map<String, Object>

        // 处理countList为null的情况，转为空列表
        List<Map> safeCountList = Optional.ofNullable(countList).orElse(Collections.emptyList());

        // 提取班级人数（stuCount）
        List<Object> dataList = safeCountList.stream()
                .map(map -> map.get("stuCount"))  // 现在键与SQL别名匹配
                .collect(Collectors.toList());

        // 提取班级名称（clazzName）
        List<Object> clazzList = safeCountList.stream()
                .map(map -> map.get("clazzName"))  // 现在键与SQL别名匹配
                .collect(Collectors.toList());

        return new StuCountOption(clazzList, dataList);
    }
}
