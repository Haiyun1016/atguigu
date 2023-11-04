package com.atguigu.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 *  定义四个增强方法，获取目标方法的信息 返回值 异常对象
 *  1. 定义方法 - 写增强代码
 *  2. 使用注解 - 指定对应的位置
 *  3. 配置切点表达式 - 选中目标方法
 *  4. 切面和IoC配置
 *  5. 开启aspectj注解的支持
 *
 *  TODO:增强方法中获取目标方法信息
 *      1. 全部增强方法中，获取目标方法的信息，包括方法名，参数，访问修饰符，所属类的信息等等
 *          在形参列表中加 JoinPoint joinPoint
 *          joinPoint 包含目标方法的信息
 *      2. 获取返回的结果 - 只在@AfterReturning中有效
 *          1. 形参中添加 （, Object result）result接收返回结果
 *          2. 需要在注解中指定形参名 (, returning = “形参名「result」”)
 *      3. 获取异常信息 - 只在@AfterThrowing中有效
 *          1. 形参中添加 （, Throwable throwable）throwable 接收异常信息
 *          2. 需要在注解中指定形参名 (, throwing = "形参名"「throwable」”)
 */

//@Component
//@Aspect
public class MyAdvice {
    @Before("com.atguigu.pointcut.MyPointCut.mypc()")
    public void before(JoinPoint joinPoint) {
        // 1. 获取方法属于的类的信息
        String simpleName = joinPoint.getTarget().getClass().getSimpleName();
        // 2. 获取方法名称
        int modifiers = joinPoint.getSignature().getModifiers();//获取访问修饰符
        String s = Modifier.toString(modifiers); //获取到的访问修饰符是int型，转化为string
        String name = joinPoint.getSignature().getName();//获取方法名称
        // 3. 获取目标方法参数列表
        Object[] args = joinPoint.getArgs();
    }

    @AfterReturning(value = "com.atguigu.pointcut.MyPointCut.mypc()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {}

    @After("com.atguigu.pointcut.MyPointCut.mypc()")
    public void after(JoinPoint joinPoint) {}

    @AfterThrowing(value = "com.atguigu.pointcut.MyPointCut.mypc()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {}
}
