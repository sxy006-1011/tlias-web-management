package com.itheima.controller;


import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptCtroller {
    @Autowired
    private DeptService deptService;
@GetMapping("/depts")
    public Result findAll() {
        System.out.println("查询所有部门信息");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
    @DeleteMapping("/depts")
    public Result deleteDept(Integer id) {
        System.out.println("删除部门信息"+id);
        deptService.deleteById(id);
        return Result.success();
    }
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        System.out.println("修改部门信息"+dept);
        deptService.add(dept);
        return Result.success();
    }
    @PutMapping("/depts/{id}")
    public Result update(@PathVariable Integer id){
        System.out.println("修改部门信息"+id);
        Dept dept=deptService.update(id);
        return Result.success(dept);
    }
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门信息"+dept);
        deptService.update1(dept);
        return Result.success();
    }
}
