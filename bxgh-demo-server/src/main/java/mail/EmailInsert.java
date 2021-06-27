package edu.jd.xyt.mail;

import edu.jd.xyt.common.Utils;
import org.apache.ibatis.session.SqlSession;

public class EmailInsert {
    public boolean insertEmail(EmailDto dto) {
        SqlSession sqlSession = Utils.openSession();

        try {
            EmailDao dao = sqlSession.getMapper(EmailDao.class);

            dao.insertEmail(dto);

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
