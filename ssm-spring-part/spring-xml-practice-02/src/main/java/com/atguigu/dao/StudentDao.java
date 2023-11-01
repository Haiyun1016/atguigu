package com.atguigu.dao;

import com.atguigu.pojo.Student;

import java.util.List;

//数据层的接口
public interface StudentDao {
    /**
     * 数据库查询全部学生数据
     */
    List<Student> queryAll();
}
