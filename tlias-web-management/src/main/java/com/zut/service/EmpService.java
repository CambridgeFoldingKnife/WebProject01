package com.zut.service;

import com.zut.pojo.Emp;
import com.zut.pojo.EmpQueryParam;
import com.zut.pojo.PagBean;

public interface EmpService  {
    PagBean page(EmpQueryParam queryParam);

    void add(Emp emp);
}
