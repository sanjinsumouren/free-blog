package com.su.blog.annotation;

import com.su.blog.enumPackage.OperationEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 自定义注解.在新增和更新用户时候自动填入时间
@Target(ElementType.METHOD)  //作用于方法
@Retention(RetentionPolicy.RUNTIME) //运行时注入
public @interface AutoTime {
    //操作类型
    OperationEnum value();
}
