package edu.jd.xyt.maildemo;

import org.apache.ibatis.annotations.Select;


public interface MailDao {
    @Select("select count(c_email) from customer where c_email = #{c_email}")
    int checkAccountExist(MailDto dto);
}
