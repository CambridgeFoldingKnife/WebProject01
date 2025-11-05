package com.zut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zut.mapper.OperateLogMapper;
import com.zut.pojo.OperateLog;
import com.zut.pojo.PagBean;
import com.zut.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

 /*   @Override
    public PagBean getLogPage(Integer page, Integer pageSize) {
        int startIndex = (page - 1) * pageSize;
        List<OperateLog> logList = operateLogMapper.selectLogByPage(startIndex, pageSize);
        Long totalCount = operateLogMapper.selectLogTotalCount();
        // 直接实例化PagBean（原始类型），传入List<OperateLog>和totalCount
        return new PagBean(totalCount, logList);
    }*/

    @Override
    public PagBean getLogPage(Integer page, Integer pageSize){
        // 设置分页参数 pageHelper
        PageHelper.startPage(page, pageSize);
        // 执行mapper
        List<OperateLog> logList = operateLogMapper.selectLogByPage(page, pageSize);
        // 获取分页结果
        Page<OperateLog> logPage = (Page<OperateLog>) logList;
        // 封装分页结果
        return new PagBean(logPage.getTotal(), logPage.getResult());
    }
}