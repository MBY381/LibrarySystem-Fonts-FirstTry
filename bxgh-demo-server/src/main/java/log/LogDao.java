package edu.jd.xyt.log;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//本注解明确说明这个接口是一个mybatis映射器
public interface LogDao {
    //这里是不是可以这样理解：使用了@给下面这个表赋予了语义，即“搜索所有的教师信息的表”
    @Select("select * from log")
    List<Log> findLogList();

    @Select("select * from log where c_id like '%${param}%' or c_name like '%${param}%'")
    List<Log> findLogList_by_data(queryDto q);

    @Update("update log set c_key = #{c_key}, c_name = #{c_name},c_email = #{c_email}  where c_id = #{c_id}")
    boolean UpdateLog(Log log);
    @Insert("insert into log (c_id,c_key,c_name,borrow_num,c_email,c_avatar_path)" +
            " values(#{c_id},#{c_key},#{c_name},#{borrow_num},#{c_email},#{c_avatar_path})")
    boolean InsertLog(Log log);

    @Delete("delete from log where c_id=#{c_id}")
    boolean DeleteLog(Log log);
}
