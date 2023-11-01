package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.atguigu.controller.StudentController;
import com.atguigu.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//测试JdbcTemplateTest如何使用
public class JdbcTemplateTest {
    public void testForJava() {

        /**
         * JdbcTemplate 简化数据库的CRUD，不提供连接池
         * 因此需要配合DruidDataSource使用，它负责连接的创建和数据库驱动的注册等等
         *
         */

        //0.创建一个连接池对象
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql:///studb");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");

        //1.实例化对象即可
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.setDataSource(dataSource); //里面放连接池对象

        //2.调用方法即可
        //jdbcTemplate.update() DDL DML DCL...非查询语句
        //jdbcTemplate.queryForObject() DQL 查询单个对象
        //jdbcTemplate.query() DQL 查询集合
    }

    /**
     * 通过IoC容器读取配置的JdbcTemplate组件
     */
    @Test
    public void testForIoC() {
        //1.创建IoC容器
        ApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("spring-01.xml");

        //2.获取JdbcTemplate组件
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);

        //3.进行数据库的CRUD
        //3.1 插入 删除 和 修改 DML update
        String sql = "insert into students (id, name, gender, age, class) values (?,?,?,?,?)";
        /**
         * update()
         * 参数1:String sql 可以带占位符 ？？ 只能替代值，不能替代关键字和容器名
         * 参数2:Object...param 传入占位符的值 顺序 从左开始对应
         * 返回值 int类型， 影响行数
         */
        int rows = jdbcTemplate.update(sql,9, "二狗子","男",18,"三年二班");
        System.out.println("rows = " + rows);
        //3.2 查询单条数据
        //根据id查询学生数据  返回一个对应的实体对象
        sql = "select * from students where id = ?;";
        /**
         * queryForObject()
         * 参数1:sql语句 可以使用占位符
         * 参数2:RowMapper 列名和属性名的映射器接口
         * 参数3:Object...param 可变参数 占位符的值
         * 返回值 rowMapper 指定的对象
         */
//        jdbcTemplate.queryForObject(sql, new RowMapper<Student>() {
//            @Override
//            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//                //rs 结果集
//                //rowNum 是行数
//                //rs结果集中获取列的值，赋值给实体类对象
//                Student student = new Student();
//                student.setId(rs.getInt("id"));
//                student.setName(rs.getString("name"));
//                student.setAge(rs.getInt("age"));
//                student.setGender(rs.getString("gender"));
//                student.setClasses(rs.getString("class"));
//                return student;
//            }
//        }, 1);
//简化,laboda表达式
        Student student1 = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                //rs 结果集
                //rowNum 是行数
                //rs结果集中获取列的值，赋值给实体类对象
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                student.setClasses(rs.getString("class"));
                return student;
        }, 1);
        System.out.println("student1 = " + student1);

        //3.3 查询所有学生数据
        sql = "select id, name, gender, age, class as classes from students ;";

        //TODO:BeanPropertyRowMapper 帮助我们自动映射列和属性值，要求列名和属性名一致，如果不一致，起别名
        List<Student> studentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
        System.out.println("studentList = " + studentList);

    }

    @Test
    /**
     * 从IoC容器中获取controller并且调用业务，内部都是IoC容器进行组装
     */
    public void testQueryAll() {
        //1.创建IoC容器
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("spring-02.xml");
        //2.获取组件对象
        StudentController controller = applicationContext.getBean(StudentController.class);
        //3.使用组件对象
        controller.findAll();
        //4.关闭容器
        applicationContext.close();

    }

}
