package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> findAll();
    public void deleteById(Integer id);
    public void add(Dept dept);

    Dept update(Integer id);
    void update1(Dept dept);
}
