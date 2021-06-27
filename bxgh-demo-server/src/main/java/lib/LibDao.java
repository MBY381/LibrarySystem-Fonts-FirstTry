package edu.jd.xyt.lib;


import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LibDao {
    @Select("select * from borrow")
    List<Library> findLibraryList ();

}
