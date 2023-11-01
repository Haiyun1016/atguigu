package com.atguigu.test;

import com.atguigu.config.JavaConfig;
import com.atguigu.service.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(value = JavaConfig.class)
public class SpringAOPTest {
    @Autowired
    private Calculator calculator;

    @Test
    public void test() {
        int add = calculator.add(1, 1);
        System.out.println("add = " + add);
    }
}
