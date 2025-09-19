package com.su.blog.aspect;

import com.su.blog.enumPackage.OperationEnum;
import com.su.blog.annotation.AutoTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoTimeAspect {
    //定义切点
    @Pointcut("execution(* com.su.blog.mapper.*.*(..)) && @annotation(com.su.blog.annotation.AutoTime)")
    //匹配当前包下的所有mapper,添加了AutoTime注解的方法
    public void pointcut() {
    }

    //方法执行前开始填充字段
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        log.info("开始填充字段");
        //切面逻辑
        //获取操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//获取到方法签名,并强转为他的方法实现类
        //获取到方法上的注解
        AutoTime annotation = signature.getMethod().getAnnotation(AutoTime.class);
        //获取方法中的参数对象
        Object[] args = joinPoint.getArgs();
        //防止空指针
        if (args == null || args.length == 0) {
            return;
        }
        //获取第一个参数
        Object arg = args[0];
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        //判断注解里面的参数是什么
        if (annotation.value().equals(OperationEnum.INSERT)) {
            //填充创建时间字段
            try {
                arg.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class).invoke(arg, localDateTime);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

        }
        if (annotation.value().equals(OperationEnum.UPDATE)) {
            //填充更新时间字段
            try {
                arg.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class).invoke(arg, localDateTime);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
