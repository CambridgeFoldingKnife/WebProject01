package com.zut.anno;
//自定义aop注解

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* 自定义标识注解
 */
@Target(ElementType.METHOD)//自定义注解的作用的位置
@Retention(RetentionPolicy.RUNTIME)//运行期间有效
public @interface Log {

}
