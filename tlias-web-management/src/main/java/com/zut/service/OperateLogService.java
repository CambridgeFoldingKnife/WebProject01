package com.zut.service;

import com.zut.pojo.PagBean;

public interface OperateLogService {
    // 返回值改为原始类型PagBean，无泛型
    PagBean getLogPage(Integer page, Integer pageSize);
}