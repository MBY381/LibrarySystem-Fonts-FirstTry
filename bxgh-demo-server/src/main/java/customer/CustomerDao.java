package edu.jd.xyt.customer;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//本注解明确说明这个接口是一个mybatis映射器
public interface CustomerDao {
    //这里是不是可以这样理解：使用了@给下面这个表赋予了语义，即“搜索所有的教师信息的表”
    @Select("select * from customer")
    List<Customer> findCustomerList();

    @Select("select * from customer where c_id like '%${param}%' or c_name like '%${param}%'")
    List<Customer> findCustomerList_by_data(queryDto q);

    @Update("update customer set c_key = #{c_key}, c_name = #{c_name},c_email = #{c_email}  where c_id = #{c_id}")
    boolean UpdateCustomer(Customer customer);
    @Insert("insert into customer (c_id,c_key,c_name,borrow_num,c_email,c_avatar_path)" +
            " values(#{c_id},#{c_key},#{c_name},#{borrow_num},#{c_email},#{c_avatar_path})")
    boolean InsertCustomer(Customer customer);

    @Delete("delete from customer where c_id=#{c_id}")
    boolean DeleteCustomer(Customer customer);
}
