package com.zut.pojo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 日志实体类
 */
@Data
public class Log {
    // 日志ID
    private Long id;
    // 操作人ID
    private Long operateEmpId;
    // 操作时间（对应接口的string类型，LocalDateTime序列化后自动匹配ISO格式）
    private LocalDateTime operateTime;
    // 操作类名
    private String className;
    // 操作方法名
    private String methodName;
    // 方法请求参数
    private String methodParams;
    // 方法返回值
    private String returnValue;
    // 执行耗时（单位：ms）
    private Integer costTime;
    // 操作人姓名
    private String operateEmpName;
}
