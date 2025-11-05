package com.zut.mapper;

import com.zut.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

    // 分页查询日志
    @Select("select ol.* ,e.name as operate_emp_name from operate_log ol left join emp e on ol.operate_emp_id = e.id ")
    List<OperateLog> selectLogByPage(int startIndex, Integer pageSize);

    // 查询日志总记录数（补充@Select注解，适配service调用）
    @Select("select count(*) from operate_log")
    Long selectLogTotalCount();
}
