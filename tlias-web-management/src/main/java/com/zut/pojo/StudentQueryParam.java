package com.zut.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
//前端仅有姓名 班级 学历.其他字段后续可扩展
@Data
public class StudentQueryParam {
    // 分页参数（默认值）
    private Integer page = 1; // 页码，默认第1页
    private Integer pageSize = 10; // 每页条数，默认10条

    // 学生查询条件（根据响应字段提取常用查询维度）
    private String name; // 学生姓名（支持模糊查询，如"Li"匹配"Lily"）
    private String no; // 学号（支持精确或模糊查询，如"2023001"）
    private Integer gender; // 性别（1-男，2-女，对应响应中的gender字段）
    private Integer degree; // 学历（对应响应中的degree字段，如4-本科）
    private Integer clazzId; // 班级ID（对应响应中的clazzId，如1-黄埔班一期）
    private String clazzName; // 班级名称（支持模糊查询，如"黄埔班"）
    private Integer isCollege; // 是否为大学生（0-否，1-是，对应响应中的isCollege）

    // 时间范围查询（可选，根据业务需求添加）
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate graduationDate; // 毕业时间（对应graduationDate）

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTime; // 创建时间开始（对应createTime）
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateTime; // 创建时间结束
}