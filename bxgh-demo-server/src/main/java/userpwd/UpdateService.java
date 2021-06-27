package edu.jd.xyt.userpwd;

import edu.jd.xyt.common.Utils;
import edu.jd.xyt.maildemo.MailAPI;
import org.apache.ibatis.session.SqlSession;

public class UpdateService {
    public static void updatepwd(UpdateDto dto) {
        dto.setC_email(MailAPI.verifiedeamil);
        System.out.println(MailAPI.verifiedeamil);
        SqlSession sqlSession = Utils.openSession();

            try {
                UpdateDao dao = sqlSession.getMapper(UpdateDao.class);
                dao.updateCustomerPwd(dto);
                sqlSession.commit();

            } catch (Exception e) {
                sqlSession.rollback();
                throw new RuntimeException(e);
            } finally {
                sqlSession.close();
            }
        }
    }

