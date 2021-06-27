package edu.jd.xyt.maildemo;


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
import java.util.Objects;


@WebServlet({
        "/sendcode",//发送验证码
        "/verifycode"//验证验证码
        })
public class MailAPI extends HttpServlet {
    //静态变量用于不同方法之间固定当前操作账户
    private static String tempeamil=null;
    public static String verifiedeamil=null;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        MailDto dto1=null;
        String json = null;
        try (Reader reader = request.getReader(); CharArrayWriter out = new CharArrayWriter()) {
            char[] charBuff = new char[100];
            int len;
            while ((len = reader.read(charBuff)) != -1) {
                out.write(charBuff, 0, len);
            }
            out.flush();
            json = out.toString();
            System.out.println(json);
            dto1 = JSON.parseObject(json, MailDto.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //发送验证码
        if ("/sendcode".equals(path)) {
            //判断邮箱格式是否正确
            if(!((Objects.requireNonNull(json).contains(".cn") || json.contains(".com")) && json.contains("@"))) {
                Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, "邮箱格式错误！"));
            }
            //判断账户是否存在于数据库中
            else if(!MailService.checkEmailExist(dto1)){
                    System.out.println(dto1);
                    Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, "账户不存在！"));
                }
            else{

            try {
                    MailService.sendemailcode(Objects.requireNonNull(dto1).getC_email());
                    Utils.outJson(response, Result.success());
                    tempeamil=dto1.getC_email(); }
            catch (Exception e) {
                    e.printStackTrace();
                    Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, e.getMessage()));
                }

            }
        }

        //验证验证码
        else if ("/verifycode".equals(path)) {

            try {

                    if (MailService.verifycode(Objects.requireNonNull(dto1).getEmailcode())) {
                        System.out.println("用户验证成功，已跳转至...");
                        Utils.outJson(response, Result.success());
                        verifiedeamil=tempeamil;
                    }else{
                        Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, "验证码错误！"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, e.getMessage()));
                }

            }
        }
    }

