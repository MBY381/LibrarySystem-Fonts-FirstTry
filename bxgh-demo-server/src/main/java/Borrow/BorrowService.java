package edu.jd.xyt.Borrow;
import edu.jd.xyt.common.Utils;
import org.apache.ibatis.session.SqlSession;

public class BorrowService {
    public boolean checkLogin(BorrowDto dto) {

        SqlSession sqlSession = Utils.openSession();

        try{
            BorrowDao dao = sqlSession.getMapper(BorrowDao.class);
           int count1=dao.findStudentID(dto);
           int count2=dao.findBookID(dto);
            return count1 > 0&count2>0;
        }catch(Exception e){
            sqlSession.rollback();
            throw new RuntimeException(e);
        }finally{
            sqlSession.close();
        }
    }
    public void insertborrow(BorrowDto dto){
        SqlSession sqlSession = Utils.openSession();

        try{
            BorrowDao dao = sqlSession.getMapper(BorrowDao.class);
            dao.insertion(dto);
            sqlSession.commit();
        }catch(Exception e){
            sqlSession.rollback();
            throw new RuntimeException(e);
        }finally{
            sqlSession.close();
        }
    }
}
