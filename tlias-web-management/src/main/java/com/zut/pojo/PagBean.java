package com.zut.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页结果的封装类
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagBean {
private Long total;//总记录数
private List rows;//查询结果数据列表
}
