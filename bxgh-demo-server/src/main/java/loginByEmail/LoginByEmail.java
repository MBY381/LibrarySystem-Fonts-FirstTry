package edu.jd.xyt.loginByEmail;

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

@WebServlet("/loginByEmail")
public class LoginByEmail extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean ok = false;
        boolean ok2 = false;
        try{
            req.setCharacterEncoding("UTF-8");

            Reader reader = req.getReader();
            CharArrayWriter out = new CharArrayWriter();

            try{
                char[] charBuff = new char[1024];
                int len=-1;

                while ((len = reader.read(charBuff))!= -1){
                    out.write(charBuff,0,len);
                }

                out.flush();

                String json = out.toString();

                LoginEmailDto dto= JSON.parseObject(json, LoginEmailDto.class);

                ok= new LoginEmailService().checkLogin(dto);

                ok2 = new LoginEmailService().checkLoginOnline(dto);
            }finally {
                out.close();
                reader.close();
            }

            if(ok){
                if(ok2){
                    Utils.outResult(resp,Result.success());
                }else {
                    Utils.outResult(resp,Result.fail(Result.ERR_CODE_ONLINE,"当前账户已经被占用...!"));
                }
            }else {
                Utils.outResult(resp,Result.fail(Result.ERR_CODE_BUSINESS,"邮箱或者密码错误...!"));
            }
        }catch (Exception e){
            Utils.outResult(resp,Result.fail(Result.ERR_CODE_SYS,"系统升级中.......!"));
        }
    }
}
