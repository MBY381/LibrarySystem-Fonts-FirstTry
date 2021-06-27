package edu.jd.xyt.maildemo;

import edu.jd.xyt.common.Utils;
import org.apache.ibatis.session.SqlSession;

import java.util.Random;

public class MailService {
    static Random r=new Random();
    static int sendcode;
    static int codestart;

    //检验账户是否存在
    public static boolean checkEmailExist(MailDto dto){
        SqlSession sqlSession = Utils.openSession();
        try{
            MailDao dao= sqlSession.getMapper(MailDao.class);
            if(dao.checkAccountExist(dto)>0){
                return true;
            }
            sqlSession.commit();
        }catch(Exception e){
            sqlSession.rollback();
            throw new RuntimeException("账户不存在！",e);
        }finally {
            sqlSession.close();
        }
        return false;
    }

    //调用SendMail函数发送邮件并固定此次发送验证码内容与开始时间点

    public static void sendemailcode(String targetmail){
        try {
            int code=r.nextInt(900000)+100000;
            codestart = (int) System.currentTimeMillis();
            SendMail.send(targetmail,"您的验证码为： \n"+ code+"\n"+"有效时间为10分钟");
            sendcode=code;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //在考虑验证码时效性的基础上校验用户输入验证码与前者已发送的验证码

    public static boolean verifycode(String emailcode) {
        try {
            if ((int) System.currentTimeMillis() - codestart > 600000) {
                sendcode = r.nextInt(999999);
            }
            return (emailcode.equals(String.valueOf(sendcode)));

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
