package com.zut.mapper;

import com.zut.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    //查询
    List<Log> getLogPage();
}
