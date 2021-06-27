package edu.jd.xyt.mail;

import java.util.Random;

public class MailService {
    static Random r=new Random();
    static int sendcode;
    static int codestart;

    //调用SendMail函数发送邮件并固定此次发送验证码与开始时间点
    public static void sendemailcode(String targetmail){
        try {
            int code=r.nextInt(900000)+100000;
            codestart = (int) System.currentTimeMillis();
            SendMail.send(targetmail,"您的验证码为：  \n"+ code);
            sendcode=code;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //在考虑验证码时效性的基础上校验用户输入验证码与前者已发送的验证码
    public static boolean verifycode(String emailcode) {
        try {
            if ((int) System.currentTimeMillis() - codestart > 6000000) {
                sendcode = r.nextInt(999999);
            }
            return (emailcode.equals(String.valueOf(sendcode)));

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
