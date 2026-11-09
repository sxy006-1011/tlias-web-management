package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clazzs")
public class ClazzControler {

  @Autowired
  private ClazzService clazzService;
  @GetMapping
    public Result getClazzList(ClazzQueryParam clazzQueryParam) {
      log.info("班级列表数据分页查询{}", clazzQueryParam);
    PageResult<Clazz> clazzList = clazzService.getClazzList(clazzQueryParam);
        return Result.success(clazzList);
    }


    //删除班级
  @DeleteMapping("/{id}")
  public Result deleteClazz(@PathVariable Integer id) {
    log.info("删除班级{}", id);
    clazzService.deleteClazz(id);
    return Result.success();
  }

  //添加班级
  @PostMapping
  public Result addClazz(@RequestBody Clazz clazz){
    log.info("添加班级{}", clazz);
    clazzService.addClazz(clazz);
    return Result.success();
  }

  @GetMapping("/{id}")
  public Result selectClazzById(@PathVariable Integer id){
    log.info("查询班级{}", id);
    Clazz clazz = clazzService.selectClazzById(id);
    return Result.success(clazz);
  }
  @PutMapping
  public Result updateClazz(@RequestBody Clazz clazz){
    log.info("修改班级{}", clazz);
    clazzService.updateClazz(clazz);
    return Result.success();
  }

  //查询所有班级
  @GetMapping("/list")
  public Result selectAllClazz(){
    log.info("查询所有班级");
    List<Clazz> clazzList = clazzService.selectAllClazz();
    return Result.success(clazzList);
  }

}
