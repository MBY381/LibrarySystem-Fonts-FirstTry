package edu.jd.xyt.mail;

import com.alibaba.fastjson.JSON;
import edu.jd.xyt.common.Result;
import edu.jd.xyt.common.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Reader;


@WebServlet({
        "/user/insertemail",
        "/user/login",
        "/user/sendcode",//发送验证码
        "/user/verifycode"//验证验证码

        })
public class MailAPI extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        String json;
        boolean exist = false;
        try (Reader reader = request.getReader(); CharArrayWriter out = new CharArrayWriter()) {
            char[] charBuff = new char[100];
            int len = -1;
            while ((len = reader.read(charBuff)) != -1) {
                out.write(charBuff, 0, len);
            }
            out.flush();
            json = out.toString();

        }

        if("/user/insertemail".equals(path)){
            EmailDto dto = JSON.parseObject(json,EmailDto.class);

            exist=new EmailInsert().insertEmail(dto);

            if(exist){
                Utils.outResult(response,Result.success());
            }else{
                Utils.outResult(response,Result.fail(Result.ERR_CODE_BUSINESS,"账户不存在"));
            }
        }


        if ("/user/sendcode".equals(path)) {

            try {
                    MailService.sendemailcode(json);
                    Utils.outJson(response, Result.success());
                } catch (Exception e) {
                    e.printStackTrace();
                    Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, e.getMessage()));
                }

        }
        //验证验证码
        else if ("/user/verifycode".equals(path)) {

            try {
                    if (MailService.verifycode(json)) {
                        System.out.println("用户验证成功，已跳转至...");
                        Utils.outJson(response, Result.success());
                    }else{
                        Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, "验证码错误！"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
                    Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, e.getMessage()));
            }


        }


        else if("/user/login".equals(path)){
            IdDto dto = JSON.parseObject(json,IdDto.class);

            exist = new IdConf().checkId(dto);

            if(exist){
                Utils.outResult(response,Result.success());
            }else{
                Utils.outResult(response,Result.fail(Result.ERR_CODE_BUSINESS,"账户不存在"));
            }
        }
    }
}

