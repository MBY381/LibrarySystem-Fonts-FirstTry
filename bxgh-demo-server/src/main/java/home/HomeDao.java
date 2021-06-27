package edu.jd.xyt.home;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HomeDao {

    @Select("select a_id,a_key,a_name,a_avatar_path from admin where a_id = #{id}")
    Admin findAdminById(String userId);


    @Update("update admin set a_avatar_path = #{avatar} where a_id = #{uid}")
    void updateAvatar(@Param("uid") String userId, @Param("avatar") String filename);
}
