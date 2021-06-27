package edu.jd.xyt.register;

import edu.jd.xyt.common.Utils;
import org.apache.ibatis.session.SqlSession;

public class RegisterService {
    public boolean insert(RegisterDto dto) {
        SqlSession sqlSession = Utils.openSession();

        try {
            RegisterDao dao = sqlSession.getMapper(RegisterDao.class);

            dao.doInsertIntoDB(dto);

            sqlSession.commit();
            return true;

        }catch (Exception e){
            sqlSession.rollback();
            throw new RuntimeException(e);
        }finally {
            sqlSession.close();
        }



    }
}
