package edu.jd.xyt.tea;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper//本注解明确说明这个接口是一个mybatis映射器
public interface TeaDao {
    //这里是不是可以这样理解：使用了@给下面这个表赋予了语义，即“搜索所有的教师信息的表”
    @Select("select * from t_teacher")
    List<Teacher> findTeacherList();
}
