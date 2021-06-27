package edu.jd.xyt.mail;

import org.apache.ibatis.annotations.Update;

public interface EmailDao {
    @Update("update admin set a_email=#{u_email} where a_id=#{u_id}")
    void insertEmail(EmailDto dto);
}
