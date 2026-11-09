package com.itheima.controller;


import com.github.pagehelper.Page;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    //学生信息分页查询
    @GetMapping
    public Result selectByPage(StudentQueryParam studentQueryParam) {
        log.info("学生信息分页查询: {}", studentQueryParam.toString());
        PageResult<Student> pageResult = studentService.selectByPage(studentQueryParam);
        return Result.success(pageResult);
    }


    //根据id删除删除学员
    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable List<Integer> ids){
        log.info("根据id删除删除学员: {}", ids);
        studentService.deleteById(ids);
        return Result.success();
    }


    //添加学员
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("添加学员: {}", student);
        studentService.addStudent(student);
        return Result.success();
    }

    //根据id查询信息
    @GetMapping("/{id}")
    public Result selectStudentById(@PathVariable Integer id){
        log.info("根据id查询信息: {}", id);
        Student student = studentService.selectStudentById(id);
        return Result.success(student);

    }

    //修改学员信息
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员信息: {}", student);
        studentService.update(student);
        return Result.success();
    }

    //违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result violationDealt(@PathVariable Integer id, @PathVariable Integer score){
        log.info("违纪处理: {},{}", id, score);
        studentService.violationDealt(id, score);
        return Result.success();

    }
            }


