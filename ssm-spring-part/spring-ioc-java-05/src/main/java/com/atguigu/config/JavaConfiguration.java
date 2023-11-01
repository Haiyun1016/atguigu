package com.atguigu.config;



import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

//Java的配置类，替代 .xml

/**
 * 要完成如下功能：
 * 1.包扫描注解配置
 * 2.引用外部的配置文件
 * 3.声明第三方依赖的bean组件
 */

/**
 * 关于上述3. 的说明
 * 一个bean标签，就换成 一个方法
 * 方法的返回值类型 == bean组件的类型或者他的接口和父类
 * 方法的名字 = bean id
 *
 * 方法体可以自定义实现过程即可
 * 最重要一步：@Bean 会真正让配置类的方法创建的组件存储到IoC容器
 *
 * 问题1： beanName的问题
 *        默认： 方法名
 *        指定： name / value 属性起名字，覆盖方法名
 *         @bean(name = "ergouzi")
 *
 * 问题2： 周期方法如何指定
 *        原有注解方案：PostConstruct + PreDestroy 注解指定
 *        bean属性指定：initMethod / destroyMethod指定
 *        @bean(name = "ergouzi", initMethod = "", destroyMethod = "")
 *
 * 问题3： 作用域
 *        用@Scope注解，默认是单例
 *        @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
 *
 * 问题4： 如何引用其他的IoC组件
 *        直接调用对方的bean方法
 *        直接形参变量进行引入，要求必须有对应的组件，如果有多个，形参名==BeanId即可
 *
 */

/**
 * 做法：
 * 步骤1：添加@Configuration注解，代表这是一个配置类
 * 步骤2：实现上面的三个功能注释
 */

@PropertySource("classpath:jdbc.properties")
@ComponentScan("com.atguigu.ioc_01")
//如果多个包@ComponentScan({"com.atguigu.ioc_01", "..."})，按照字符串格式
@Configuration
public class JavaConfiguration {
    @Value("${atguigu.url}")
    private String url;
    @Value("${atguigu.driver}")
    private String driver;
    @Value("${atguigu.username}")
    private String username;
    @Value("${atguigu.password}")
    private String password;
    @Bean
    public DruidDataSource dataSource() {
        //在这里实现具体的实例化过程
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        //因为需要用到DataSource，符合需要IoC容器的其他组件的情况
        //方案1:如果其他组件，也是@Bean方法，可以直接调用
        //虽然是直接调用，但本质上仍然是从IoC容器中获取的组件，不推荐
        jdbcTemplate.setDataSource(dataSource());

        return jdbcTemplate;
    }

    public JdbcTemplate jdbcTemplate1(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        //因为需要用到DataSource，符合需要IoC容器的其他组件的情况
        //方案2:利用形参列表获取，可以是一个也可以多个，IoC容器也会注入
        //这种方式要求必须要有对应类型的组件，如果没有则会抛异常
        //如果有多个组件，可以使用形参名称 == Bean的id
        jdbcTemplate.setDataSource(dataSource);

        return jdbcTemplate;
    }
}
