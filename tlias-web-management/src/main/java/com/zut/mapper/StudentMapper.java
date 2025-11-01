package com.zut.mapper;

import com.zut.pojo.Student;
import com.zut.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    // 批量删除
   
//    查询学生列表
    List<Student> list(StudentQueryParam studentQueryParam);

    void deleteByIds(@Param("ids") List<Integer> ids);

    void insert(Student student);

    Student getById(Integer id);

    void update(Student student);

//扣分
    void Violation(Integer id, Integer score);
     @MapKey("degree")
    List<Map> countByDegree();
    @MapKey("count")
    List<Map> getStuCountData();
}
