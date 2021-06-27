package edu.jd.xyt.loginByEmail;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface LoginEmailDao {
    @Select("select count(a_email) from admin where a_email=#{u_email} and a_key=#{u_pwd}")
    int doCheckUserEmail(LoginEmailDto dto);

    @Select("select count(a_email) from admin where a_email=#{u_email} and a_status=0 ")
    int doFindUserNow(LoginEmailDto dto);

   @Update("update admin set a_status=1 where a_email=#{u_email}")
    void updateNow(LoginEmailDto dto);
}
