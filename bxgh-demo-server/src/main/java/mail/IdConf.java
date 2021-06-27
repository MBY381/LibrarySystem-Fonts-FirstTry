package edu.jd.xyt.mail;

import edu.jd.xyt.common.Utils;
import org.apache.ibatis.session.SqlSession;

public class IdConf {
    public boolean checkId(IdDto dto) {
        SqlSession sqlSession = Utils.openSession();

        try{
            IdDao dao = sqlSession.getMapper(IdDao.class);

            int count = dao.doFindId(dto);

            sqlSession.commit();
            return count>0;

        }catch (Exception e){
            sqlSession.rollback();
            throw new RuntimeException(e);
        }finally {
            sqlSession.close();
        }
    }
}
