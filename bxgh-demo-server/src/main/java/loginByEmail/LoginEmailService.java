package edu.jd.xyt.loginByEmail;

import edu.jd.xyt.common.Utils;
import org.apache.ibatis.session.SqlSession;

public class LoginEmailService {
    public boolean checkLogin(LoginEmailDto dto) {
        SqlSession sqlSession = Utils.openSession();

        try {
        LoginEmailDao dao= sqlSession.getMapper(LoginEmailDao.class);

        int count = dao.doCheckUserEmail(dto);

        sqlSession.commit();

        return count>0;
    }catch (Exception e){
        sqlSession.rollback();
        throw new RuntimeException(e);
    }finally {
        sqlSession.close();
    }
}

    public boolean checkLoginOnline(LoginEmailDto dto) {
       SqlSession sqlSession= Utils.openSession();

        try {
            LoginEmailDao dao = sqlSession.getMapper(LoginEmailDao.class);

            int count = dao.doFindUserNow(dto);

            sqlSession.commit();

            if(count>0){
                LoginEmailService.updateNow(dto);
            }

            return count>0;
        }catch (Exception e){
            sqlSession.rollback();
            throw new RuntimeException(e);
        }finally {
            sqlSession.close();
        }
    }

    private static void updateNow(LoginEmailDto dto) {
        SqlSession sqlSession = Utils.openSession();

        try {
            LoginEmailDao dao = sqlSession.getMapper(LoginEmailDao.class);

            dao.updateNow(dto);

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }


}
