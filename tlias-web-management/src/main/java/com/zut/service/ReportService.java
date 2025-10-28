package com.zut.service;

import com.zut.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    //统计职位人数
    JobOption empJobData();

    //统计性别人数
    List<Map> empGenderData();
}
