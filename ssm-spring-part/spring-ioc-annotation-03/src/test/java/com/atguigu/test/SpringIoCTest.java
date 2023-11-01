package com.atguigu.test;

import com.atguigu.ioc_01.XxxDao;
import com.atguigu.ioc_02.JavaBean;
import com.atguigu.ioc_03.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIoCTest {
    @Test
    public void testIoC_01() {
        //1.创建IoC容器
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("spring-01.xml");
        //2.获取组件
        XxxDao bean = applicationContext.getBean(XxxDao.class);
        System.out.println("bean = " + bean);
        //添加IoC注解，默认的组件名为 类的首字母小写
        Object xxxService = applicationContext.getBean("xxxService");
        System.out.println("xxxService = " + xxxService);
        //3.close容器
    }

    @Test
    public void testIoC_02() {
        //1.创建IoC容器
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("spring-02.xml");
        //2.获取组件
        JavaBean bean = applicationContext.getBean(JavaBean.class);
        JavaBean bean1 = applicationContext.getBean(JavaBean.class);
        System.out.println(bean == bean1);
        //3.close容器
        applicationContext.close();
    }

    @Test
    public void testIoC_03() {
        //1.创建IoC容器
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("spring-03.xml");
        //2.获取组件
        UserController userController = applicationContext.getBean(UserController.class);
        //场景1，IoC容器中有一个UserService接口对应的实现类对象！
        userController.show();
        //场景2，IoC容器没有默认的类型如何处理
        //@Autowired 使用它进行装配，默认情况下至少有一个bean，否则报错
        //场景3，同一个类型有多个对应的组件 @Autowired也会报错，无法选择哪一个
            //解决1:成员属性名指定@Autowired 多个组件的时候，默认会根据成员属性名查找
            //解决2:使用@Qualifier，这个注解不能单独存在，必须和@Autowired搭配使用。 @Qualifier(value = "userServiceImpl")
            //优化点：整合注解，@Autowired + @Qualifier == @Resource(name="userServiceImpl")
        //3.close容器
        applicationContext.close();
    }

    @Test
    public void testIoC_04() {
        //1.创建IoC容器
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("spring-04.xml");
        //2.获取组件
        com.atguigu.ioc_04.JavaBean bean = applicationContext.getBean(com.atguigu.ioc_04.JavaBean.class);
        System.out.println("bean = " + bean);

        //3.close容器
        applicationContext.close();
    }
}
