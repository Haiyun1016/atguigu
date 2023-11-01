package com.atguigu.service;

import com.atguigu.pojo.Student;

import java.util.List;

//学生业务接口
public interface StudentService {
    /**
     * 查询所有学员数据业务
     * @return
     */
    List<Student> findAll();

}
