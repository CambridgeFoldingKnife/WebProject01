package com.zut.service.impl;

import com.zut.mapper.EmpMapper;
import com.zut.pojo.JobOption;
import com.zut.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportServiceImpl  implements ReportService {
    @Autowired
    private EmpMapper empMapper;



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
}
