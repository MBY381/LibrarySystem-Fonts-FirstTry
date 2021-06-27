package edu.jd.xyt.maildemo;

import com.sun.mail.util.MailSSLSocketFactory;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {

    public static void send(String targetmail, String content) throws Exception {

        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");  //设置邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议SMTP
        prop.setProperty("mail.smtp.auth", "true"); // 验证用户名密码

        // 邮箱设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);


        //创建定义整个应用程序所需的环境信息的 Session 对象

        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication("2014894101@qq.com", "gaffsabrnsjmceff");
            }
        });


        //开启Session的debug模式，可以查看到程序发送Email的运行过程
        session.setDebug(true);

        //通过session得到transport对象
        Transport ts = session.getTransport();

        //使用邮箱的用户名和授权码连上邮件服务器，这里使用已完成POP3\SMTP授权的我的账号
        ts.connect("smtp.qq.com", "2014894101@qq.com", "gaffsabrnsjmceff");

        //创建邮件对象
        MimeMessage message = new MimeMessage(session);

        //指明邮件的发件人
        message.setFrom(new InternetAddress("2014894101@qq.com"));

        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(targetmail));

        //邮件的标题
        message.setSubject("八仙过海图书管理系统");

        //邮件的文本内容,目前尚未尝试html格式
        message.setContent(content, "text/html;charset=UTF-8");

        //发送邮件
        ts.sendMessage(message, message.getAllRecipients());

        ts.close();
    }

}

