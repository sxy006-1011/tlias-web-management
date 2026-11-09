package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;


    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
  /*      //1.查询总记录数
        Long total = empMapper.count();*/

/*        //2.查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);*/
/*
        //3.封装
        return new PageResult<Emp>(total, rows);
  ------------------------------------------------------------------------------------------
        */


     /*   PageHelper.startPage(page, pageSize);//先把分页参数传进去
        Page<Emp> p = empMapper.list(name, gender, begin,end);
        return new PageResult<>(p.getTotal(), p.getResult());*/

        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());//先把分页参数传进去
        Page<Emp> p = empMapper.list(empQueryParam);
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) throws Exception {

           try{ //1.保存基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.添加员工工作经历
            List<EmpExpr> exprList = emp.getEmpExprList();
            if(!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(expr -> {
                    expr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
            }finally{
                   //记录操作日志
                   EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "添加了员工：" + emp);
                   empLogService.insertLog(empLog);
               }
        }


    @Transactional(rollbackFor = Exception.class)//只要出现异常就会滚
    @Override
    public void delete(List<Integer> ids){
      //1.删除员工基本信息
        empMapper.deleteByIds(ids);
        //2.删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

@Override
    public Emp getInfo(Integer id){
        return empMapper.getById(id);
    }

    //修改员工信息
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp){
        //1.修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        //2.修改员工工作经历
        //删除员工工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //添加员工工作经历
        List<EmpExpr> exprList = emp.getEmpExprList();
        if(!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> {
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }
    public LoginInfo login(Emp emp){
        //1.根据用户名和密码查询员工
       Emp e = empMapper.selectByUsernameAndPassword(emp);
        //2.判断员工是否存在
        if (e != null) {
            log.info("员工存在{}", e);
            //生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String token =JwtUtils.generateJwt(claims);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), token);
        }
        else {
            log.info("员工不存在");
            return null;
        }
    }
}




