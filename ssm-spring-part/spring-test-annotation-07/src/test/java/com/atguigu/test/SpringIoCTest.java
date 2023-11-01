package com.atguigu.test;

import com.atguigu.components.A;
import com.atguigu.config.JavaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(value = JavaConfig.class)
public class SpringIoCTest {

    @Autowired
    private A a;
    @Test
    public void test() {
        //1.IoC容器

        //2.获取组件

        //3.使用组件
        System.out.println("a = " + a);
    }
}
