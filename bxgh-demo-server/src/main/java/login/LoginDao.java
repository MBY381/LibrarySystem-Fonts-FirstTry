package edu.jd.xyt.login;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface LoginDao {
    @Select("select count(a_id) from admin where a_id=#{u_id} and a_key=#{u_pwd}")
    int doFindUserCountByIdAndPwd(LoginDto dto);


    @Select("select count(a_id) from admin where a_id=#{u_id} and a_status=0 ")
    int doFindUserNow(LoginDto dto);

    @Update("update admin set a_status=1 where a_id=#{u_id}")
    void updateNow(LoginDto dto);
}
