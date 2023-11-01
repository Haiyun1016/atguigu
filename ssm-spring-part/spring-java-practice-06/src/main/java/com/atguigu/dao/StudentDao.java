package com.atguigu.dao;

import com.atguigu.pojo.Student;

import java.util.List;

//学员持久层访问接口
public interface StudentDao {
    List<Student> queryAll();
}
