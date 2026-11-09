package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult<Student> selectByPage(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());

        Page<Student> page = studentMapper.selectByPage(studentQueryParam);
        return new PageResult<>(page.getTotal(), page.getResult());
    }
    @Override
    public void deleteById(List<Integer> ids) {
        studentMapper.deleteById(ids);
    }
    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.addStudent(student);
    }
    @Override
    public Student selectStudentById(Integer id) {
        return studentMapper.selectStudentById(id);
    }
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void violationDealt(Integer id, Integer score) {
        studentMapper.violationDealt(id, score);
    }
}
