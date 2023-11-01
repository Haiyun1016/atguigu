package com.atguigu.test;

import com.atguigu.config.JavaConfiguration;
import com.atguigu.config.JavaConfigurationA;
import com.atguigu.config.JavaConfigurationB;
import com.atguigu.ioc_01.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringIoCTest {
    @Test
    public void test() {
        //1. 创建IoC容器
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(JavaConfiguration.class);
            //外部设置配置类
//        AnnotationConfigApplicationContext annotationConfigApplicationContext
//                = new AnnotationConfigApplicationContext();
//        annotationConfigApplicationContext.register(JavaConfiguration.class);
//        annotationConfigApplicationContext.refresh(); //有这一步，IoC才会有DI动作

        //2. 获取bean
        StudentController studentController = applicationContext.getBean(StudentController.class);

        System.out.println("studentController = " + studentController);

    }

    @Test
    public void test_04() {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(JavaConfigurationA.class, JavaConfigurationB.class);
    }
}
