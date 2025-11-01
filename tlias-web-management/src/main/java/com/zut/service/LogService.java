package com.zut.service;

import com.zut.pojo.PagBean;

public interface LogService {
    PagBean getLogPage(Integer page, Integer pageSize);
}
