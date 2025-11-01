package com.zut.service;

import com.zut.pojo.JobOption;
import com.zut.pojo.StuCountOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    //统计职位人数
    JobOption empJobData();

    //统计性别人数
    List<Map> empGenderData();

    List<Map> studentDegreeData();

    StuCountOption studentCountData();
}
