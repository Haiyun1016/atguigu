package com.atguigu.advice;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(10) //指定一个优先级的值，值越小优先级越高，越高的前置先执行，后置后执行
public class TxAdvice {
    @Before("com.atguigu.pointcut.MyPointCut.pc()")
    public void begin() {
        System.out.println("TxAdvice.begin");
    }

    @AfterReturning("com.atguigu.pointcut.MyPointCut.pc()")
    public void commit() {
        System.out.println("TxAdvice.commit");
    }

    @AfterThrowing("com.atguigu.pointcut.MyPointCut.pc()")
    public void rollback() {
        System.out.println("TxAdvice.rollback");
    }
}
