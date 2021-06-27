package edu.jd.xyt.book;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.jd.xyt.common.Utils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {

    public static void updateBook(Book book) {
        SqlSession sqlSession = Utils.openSession();

        try{
            BookDao dao = sqlSession.getMapper(BookDao.class);
            boolean tmp=dao.UpdateBook(book);
            System.out.println(tmp);
            System.out.println("???");
            sqlSession.commit();
        }catch(Exception e){
            System.out.println("更新失败");
            e.printStackTrace();
            sqlSession.rollback();
            throw new RuntimeException(e);
        }finally{

            sqlSession.close();
        }
    }

    public static void insertBook(Book book) {

        SqlSession sqlSession = Utils.openSession();

        try{
            BookDao dao = sqlSession.getMapper(BookDao.class);
            boolean tmp=dao.InsertBook(book);
            System.out.println(tmp);
            System.out.println("???");
            sqlSession.commit();
        }catch(Exception e){
            System.out.println("更新失败");
            e.printStackTrace();
            sqlSession.rollback();
            throw new RuntimeException(e);
        }finally{

            sqlSession.close();
        }
    }

    public static void deleteBook(Book book) {

        SqlSession sqlSession = Utils.openSession();

        try{
            BookDao dao = sqlSession.getMapper(BookDao.class);
            boolean tmp=dao.DeleteBook(book);
            System.out.println(tmp);
            System.out.println("???");
            sqlSession.commit();
        }catch(Exception e){
            System.out.println("更新失败");
            e.printStackTrace();
            sqlSession.rollback();
            throw new RuntimeException(e);
        }finally{

            sqlSession.close();
        }
    }

    public Map<String,Object> getBookList(queryDto q) {
        SqlSession sess = Utils.openSession();
    try{
           //接口名.class，这里是把接口给实例化了，然后扔进getMapper里
            BookDao dao=sess.getMapper(BookDao.class);
            //或许可以这样理解，dao拥有这个interface有的一切东西，只不过接口里是空的，而“dao”里头装满了?
            List<Book> bookList;
            String param= q.getParam();
            PageHelper.startPage(q);
            if(param==null||param==""){
                bookList = dao.findBookList();
            }
            else {
                System.out.println(param);
                System.out.println("haha");
                bookList = dao.findBookList_by_data(param);
            }

            PageInfo<Book> pageInfo= new PageInfo<Book>(bookList);
            Map<String,Object> page=new HashMap<>();
            page.put("current", pageInfo.getPageNum());//当前页
            page.put("pageSize", pageInfo.getPageSize());//每页最大记录数
            page.put("total", pageInfo.getTotal());//每页最大记录数
            page.put("pages", pageInfo.getPages());//总页数
            page.put("size", pageInfo.getSize());//当前页实际记录数
            page.put("list",pageInfo.getList());
            page.put("first", 1);//每页最大记录数
            page.put("pre", pageInfo.getPrePage());
            page.put("next",pageInfo.getNextPage());
            page.put("last", pageInfo.getPages());

            sess.commit();//??commit??这个sess不是用来拿的吗
            return page;
    }
    catch(Exception e){
            sess.rollback();
            throw new RuntimeException("查询书本信息失败！",e);
    }
    finally {
            sess.close();
    }
    }

}
