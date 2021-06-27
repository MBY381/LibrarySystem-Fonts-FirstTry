package edu.jd.xyt.mail;

import org.apache.ibatis.annotations.Select;

public interface IdDao {
    @Select("select count(a_id) from admin where a_id=#{u_id}")
    int doFindId(IdDto dto);
}
