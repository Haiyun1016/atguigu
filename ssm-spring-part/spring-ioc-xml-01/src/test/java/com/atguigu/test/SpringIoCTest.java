package com.atguigu.test;

import com.atguigu.ioc_03.HappyComponent;
import com.atguigu.ioc_04.JavaBean2;
import com.atguigu.ioc_05.JavaBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIoCTest {
    //讲解如何创建IoC容器并且读取配置文件
    public void createIoC() {
        //创建容器，选择合适的容器实现即可
        /*
        * 接口
        *  BeanFactory
        *
        *  ApplicationContext
        *
        * 实现类
        *   可以直接通过构造函数实例化，
        *   ClassPathXmlApplicationContext 读取类路径下的xml配置方式
        *   FileSystemXmlApplicationContext  读取指定文件位置的xml配置方式
        *   AnnotationConfigApplicationContext 读取配置类方式的IoC容器
        *   WebApplicationContext              Web项目专属的配置IoC容器
         */

        //方式1：直接创建容器，并且指定配置文件
        //构造函数（Spring...配置文件） 可以填写一个或多个
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-03.xml");

        //方式2:先创建IoC容器对象，再指定配置文件，再刷新
        //通常是源码的配置过程
        ClassPathXmlApplicationContext applicationContext1 = new ClassPathXmlApplicationContext();
        applicationContext1.setConfigLocations("Spring-03.xml"); //外部配置文件的设置
        applicationContext1.refresh(); //刷新，调用IoC和DI的流程
    }

    //讲解如何在IoC容器中获取组件
    @Test
    public void getBeanFromIoC() {
        //1、创建IoC容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocations("spring-03.xml");
        applicationContext.refresh();//进行IoC和DI的动作
        //2、读取IoC容器组件
        //方案1:直接根据beanId获取  返回值类型是Object，需要强转 （不推荐）
        //Object happyComponent = applicationContext.getBean("happyComponent");
        HappyComponent happyComponent = (HappyComponent) applicationContext.getBean("happyComponent");
        //方案2:根据beanId，同时指定bean的类型 Class
        HappyComponent happyComponent1 = applicationContext.getBean("happyComponent", HappyComponent.class);
        //方案3:直接根据类型获取
        HappyComponent happyComponent2 = applicationContext.getBean(HappyComponent.class);

        happyComponent2.doWork();

        System.out.println(happyComponent == happyComponent1);
        System.out.println(happyComponent2 == happyComponent1);
        //方案3可能存在的问题，根据Bean的类型获取，同一个类型，在IoC容器中只能有一个bean
    }

    /**
     * 测试IoC配置和销毁方法的触发
     */
    @Test
    public void test_04() {
        //1.创建IoC容器，就会进行组件对象的实例化 -> init
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("spring-04.xml");
        //IoC容器调用destroy ，执行完IoC会立即释放，没来得及通知.close()
        //2.正常结束IoC容器

        JavaBean2 bean = applicationContext.getBean(JavaBean2.class);
        JavaBean2 bean1 = applicationContext.getBean(JavaBean2.class);
        System.out.println(bean == bean1);
        applicationContext.close();
    }

    /**
     * 读取使用factoryBean工厂配置的组件对象
     */
    @Test
    public void test_05() {
        //1.创建IoC容器，就会进行组件对象的实例化 -> init
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("spring-05.xml");
        //2.读取组件
        JavaBean javaBean = applicationContext.getBean("javaBean", JavaBean.class);
        System.out.println("javaBean = " + javaBean);

        //TODO:FactoryBean工厂也会加入到IoC容器，名字:&id
        Object bean = applicationContext.getBean("&javaBean");
        System.out.println("bean = " + bean);
        //3.正常结束IoC容器
        applicationContext.close();
    }
}
