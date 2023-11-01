package com.atguigu.ioc;

import com.atguigu.controller.StudentController;
import com.atguigu.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIoCTest {
    @Test
    public void test() {
        ApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("spring.xml");

        StudentController controller = applicationContext.getBean(StudentController.class);

        controller.findAll();

    }
}
