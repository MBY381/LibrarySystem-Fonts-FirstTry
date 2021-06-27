package edu.jd.xyt.book;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//本注解明确说明这个接口是一个mybatis映射器
public interface BookDao {
    //这里是不是可以这样理解：使用了@给下面这个表赋予了语义，即“搜索所有的教师信息的表”
    @Select("select * from book")
    List<Book> findBookList();

    @Select("select * from book where b_ISBN like '%${param}%' or b_name like '%${param}%' " +
            "or b_author like '%${param}%' or b_translator like '%${param}%' or " +
            "b_introduce like '%${param}%' or b_press like '%${param}%' ")
    List<Book> findBookList_by_data(String param);


    @Update("update book set b_name = #{b_name} , b_ISBN = #{b_ISBN},b_class = #{b_class},b_stock = #{b_stock}, " +
            " b_location = #{b_location} ,b_press = #{b_press}, " +
            "b_translator = #{b_translator}, b_author = #{b_author},b_total = #{b_total}, " +
            "b_class = #{b_class},b_stock = #{b_stock} , "+
            "b_price = #{b_price}, b_cover_path = #{b_cover_path},b_introduce = #{b_introduce} "+
            "where b_ISBN = #{b_ISBN}")
    boolean UpdateBook(Book book);
/*
    @Insert("insert into book table_name " +
            "(b_ISBN,b_name,b_class,b_location,b_press," +
            "b_translator,b_author,b_class," +
            "b_stock,b_price,b_cover_path) " +

            "values (#{b_ISBN},#{b_name},#{b_class},#{b_location},#{b_press}," +
            "#{b_translator},#{b_author},#{b_class}," +
            "#{b_stock},#{b_price},#{b_cover_path})")

 */
@Insert("insert into book " +
        "(b_ISBN,b_name,b_location,b_press," +
        "b_translator,b_author,b_class," +
        "b_stock,b_price,b_cover_path) " +

        "values (#{b_ISBN},#{b_name},#{b_location},#{b_press}," +
        "#{b_translator},#{b_author},#{b_class}," +
        "#{b_stock},#{b_price},#{b_cover_path})")
    boolean InsertBook(Book book);


@Delete("delete from book where b_ISBN=#{b_ISBN}")
    boolean DeleteBook(Book book);
}
