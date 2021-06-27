package edu.jd.xyt.userpwd;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UpdateDao {
    @Update("update customer set c_key = #{c_pwd1} where c_email = #{c_email}")
    void updateCustomerPwd(UpdateDto dto);
}
