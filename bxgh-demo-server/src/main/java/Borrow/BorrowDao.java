package edu.jd.xyt.Borrow;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;

public interface BorrowDao {
    @Select("select count(c_id) from customer where c_id = #{c_id}")
     int findStudentID(BorrowDto dto);
    @Select("select count(b_ISBN) from book where b_ISBN = #{isbn}")
    int findBookID(BorrowDto dto);
    @Insert("insert into borrow(borrow_id,b_ISBN,c_id,borrow_time) values (" +
            "SELECT max(borrow_id)+1  from borrow" +
            ",#{isbn},#{c_id},#{borrow_time})")
    void insertion(BorrowDto dto);
}
