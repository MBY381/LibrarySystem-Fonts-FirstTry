package edu.jd.xyt.login;

import edu.jd.xyt.common.Utils;
import org.apache.ibatis.session.SqlSession;

public class LoginService {
    public boolean checkLogin(LoginDto dto) {

        SqlSession sqlSession = Utils.openSession();

        try {
            LoginDao dao = sqlSession.getMapper(LoginDao.class);
            int count = dao.doFindUserCountByIdAndPwd(dto);

            sqlSession.commit();



            return count>0;
        }catch (Exception e){
            sqlSession.rollback();
            throw  new RuntimeException(e);
        }finally {
            sqlSession.close();
        }

    }

    public boolean checkLoginOnline(LoginDto dto) {
        SqlSession sqlSession = Utils.openSession();

        try {
            LoginDao dao= sqlSession.getMapper(LoginDao.class);
            int count = dao.doFindUserNow(dto);

            sqlSession.commit();

            if(count>0){
                LoginService.updateNowMethod(dto);
            }

            return count>0 ;
        }catch (Exception e){
            sqlSession.rollback();
            throw  new RuntimeException(e);
        }finally {
            sqlSession.close();
        }
    }

    public static void updateNowMethod(LoginDto dto){
        SqlSession sqlSession= Utils.openSession();

        try {
            LoginDao dao= sqlSession.getMapper(LoginDao.class);

            dao.updateNow(dto);

            sqlSession.commit();

        }catch (Exception e){
            sqlSession.rollback();
            throw  new RuntimeException(e);
        }finally {
            sqlSession.close();
        }
    }
}
