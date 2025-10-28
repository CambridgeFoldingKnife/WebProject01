package com.zut.mapper;

import com.zut.pojo.Student;
import com.zut.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    // 批量删除
   
//    查询学生列表
    List<Student> list(StudentQueryParam studentQueryParam);

    void deleteByIds(@Param("ids") List<Integer> ids);
}
