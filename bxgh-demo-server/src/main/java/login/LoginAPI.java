package edu.jd.xyt.login;

import com.alibaba.fastjson.JSON;
import edu.jd.xyt.common.CurrentUser;
import edu.jd.xyt.common.Result;
import edu.jd.xyt.common.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Reader;

@WebServlet("/login")
public class LoginAPI extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean ok = false;
        boolean ok2 = false;
        LoginDto dto = null;
        try {
            req.setCharacterEncoding("UTF-8");

            Reader reader = req.getReader();
            CharArrayWriter out = new CharArrayWriter();

            try {
                char[] charBuff = new char[1024];
                int len = -1;

                while ((len = reader.read(charBuff)) != -1) {
                    out.write(charBuff, 0, len);
                }
                out.flush();

                String json = out.toString();

                dto = JSON.parseObject(json, LoginDto.class);

                ok = new LoginService().checkLogin(dto);

                ok2= new LoginService().checkLoginOnline(dto);




            } finally {
                out.close();
                reader.close();
            }

            if (ok) {
                HttpSession session = req.getSession();
                session.setAttribute(CurrentUser.SESSION_ATTR_NAME,new CurrentUser(dto.getU_id()));

                    Utils.outResult(resp, Result.success());



            } else {
                Utils.outResult(resp, Result.fail(Result.ERR_CODE_BUSINESS, "账号或者密码错误"));
            }

        } catch (Exception e) {
            Utils.outResult(resp, Result.fail(Result.ERR_CODE_SYS, "系统升级中......"));
        }


    }

}
