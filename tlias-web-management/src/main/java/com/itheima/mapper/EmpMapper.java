package com.itheima.mapper;


import com.github.pagehelper.Page;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.studentCountData;
import com.itheima.pojo.studentDegreeData;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//操作员工信息
@Mapper
public interface EmpMapper {

    //查询总记录数
   /* @Select("select count(*) from emp e left outer join dept d on e.dept_id = d.id")
    public Long count();
*/

   /* //分页查询
    @Select("select e.*,d.name as dept_name from emp e left outer join dept d on e.dept_id = d.id " +
            "order by e.update_time desc limit #{start}, #{pageSize}")
    public List<Emp> list(Integer start, Integer pageSize);*/


   // @Select("select e.*,d.name from emp e left join dept d " +
           // "on e.dept_id = d.id order by e.update_time desc")


    public Page<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
    "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);

//根据员工id删除信息
    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void update(Emp emp);

    List<Map<String, Object>> countEmpJobData();

    List<Map<String, Object>> countEmpGenderData();


    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
