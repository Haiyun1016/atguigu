package com.atguigu.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class TxAroundAdvice {
    /**
     * 环绕通知，是需要你在通知中，定义目标方法的执行
     * 其中joinPoint 是目标方法（可以获取目标方法信息，多了一个执行方法）
     * return是目标方法的返回值
     *
     */

    @Around("com.atguigu.pointcut.MyPointCut.pc()")
    public Object transaction(ProceedingJoinPoint joinPoint) {
        //保证目标方法被执行即可
        Object[] args = joinPoint.getArgs();
        Object result = null;
        try {
            //此处加增强代码，相当于Before
            System.out.println("开启事务");
            result = joinPoint.proceed(args);
            System.out.println("结束事务");
        } catch (Throwable e) {
            //必须抛出异常
            System.out.println("事务回滚");
            throw new RuntimeException(e);
        }
        finally {

        }
        return null;
    }
}
