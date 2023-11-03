package com.atguigu.advice;

//增强类，内容要存储增强代码

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.test.context.event.annotation.PrepareTestInstance;

/**
 * 1. 定义方法存储增强代码
 *      具体定义几个方法，根据插入的位置决定
 * 2. 使用注解配置，指定插入目标方法的位置
 *      前置  @Before
 *      后置  @AfterReturning
 *      异常  @AfterThrowing
 *      最后  @After
 *      环绕  @Around
 *
 *      相当于try {
 *          【前置】
 *          目标方法执行
 *          【后置】
 *      }catch() {
 *          【异常】
 *      }finally {
 *          【最后】
 *      }
 *
 * 3. 配置切点表达式【选中插入的方法-->切点】
 *
 * 4. 补全注解
 *      1. 加入IoC容器，@Component
 *      2. 配置切面，@Aspect = 切面 + 增强
 *
 * 5. 开启aspect注解的支持
 *      配置文件 / 配置类
 */
@Component
@Aspect
public class LogAdvice {
    /**
     * TODO: 切点表达式
     *      固定语法 execution(1 2 3.4.5(6))
     *      1. 访问修饰符
     *          public / private
     *      2. 方法的返回参数类型
     *          String / int / void
     *          如果不考虑访问修饰符和返回值，这两位整合在一起写 * ，表示不考虑
     *          如果要是不考虑，必须两个都不考虑
     *      3. 包的位置
     *          具体包：com.atguigu.service.impl
     *          单层模糊：com.atguigu.service.*
     *          多层模糊：com..impl   ..是任意层的模糊，..不能开头
     *              不能..impl，可以*..impl
     *      4. 类的名称
     *          具体：CalculatorPureImpl
     *          模糊：*
     *          部分模糊：*Impl
     *      5. 方法名  语法和类名一致
     *      6. () 形参列表
     *          没有参数：()
     *          有具体参数：(String) (String, int) 顺序是有要求的
     *          模糊参数：(..) 有没有参数都行，有多个也可以
     *          部分模糊：(String..) 第一个参数要求是String，后面有没有无所谓
     *                  (..int) 最后一个参数是int，
     *                  (String..int) 第一个是String 最后一个是int
     */

    /**
     * TODO:切点表达式的提取和复用
     *  1. 当前类中提取
     *      定义一个空方法
     *      使用注解@Pointcut()
     *      在增强注解中引用切点表达式的方法即可
     *  2. 创建一个存储切点的类（推荐）
     *      单独维护切点表达式
     *      其他类的切点方法，加上类的全限定符.方法名()
     */


    @Before("com.atguigu.pointcut.MyPointCut.pc()")
    public void start() {
        System.out.println("方法开始了");
    }

    @After("com.atguigu.pointcut.MyPointCut.pc()")
    public void after() {
        System.out.println("方法结束了");
    }
    @AfterThrowing("com.atguigu.pointcut.MyPointCut.pc()")
    public void error() {
        System.out.println("方法报错了");
    }
}
