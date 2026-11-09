package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> selectByPage(StudentQueryParam studentQueryParam);

    void deleteById(List<Integer> id);

    void addStudent(Student student);

    Student selectStudentById(Integer id);

    void update(Student student);

    void violationDealt(Integer id, Integer score);
}
