package edu.jd.xyt.register;

import org.apache.ibatis.annotations.Insert;

public interface RegisterDao {
    @Insert("insert into admin values (#{u_id},#{u_pwd},'NULL','NULL','0','')")
    void doInsertIntoDB(RegisterDto dto);
}
