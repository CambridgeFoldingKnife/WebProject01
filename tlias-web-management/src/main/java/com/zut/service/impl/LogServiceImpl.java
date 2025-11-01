package com.zut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zut.mapper.LogMapper;
import com.zut.pojo.Log;
import com.zut.pojo.PagBean;
import com.zut.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public PagBean getLogPage(Integer page, Integer pageSize) {
        try {
            //开启分页
            PageHelper.startPage(page,pageSize);
            //查询分页结果
            List<Log> logList = logMapper.getLogPage();
            //将分页信息封装到PagBean对象中
            PageInfo<Log> pageInfo = new PageInfo<>(logList);
            //转为自己的PagBean结果
            PagBean pagBean = new PagBean(pageInfo.getTotal(),pageInfo.getList());
            return pagBean;
        } catch (Exception e) {
            //异常的时候返回一个空的PagBean对象，避免出现data市null情况
            return new PagBean(0L, Collections.emptyList());
        }
    }
}
