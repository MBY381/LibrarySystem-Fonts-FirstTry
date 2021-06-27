package edu.jd.xyt.register;

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


@WebServlet("/register")
public class RegisterAPI extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean ok;
        try {
            req.setCharacterEncoding("UTF-8");

            Reader reader = req.getReader();
            CharArrayWriter out = new CharArrayWriter();
            try {

                char[] charBuff = new char[1024];
                int len=-1;

                while((len=reader.read(charBuff))!=-1){
                    out.write(charBuff,0,len);
                }

                out.flush();

                String json = out.toString();

                RegisterDto dto = JSON.parseObject(json,RegisterDto.class);

                RegisterService service = new RegisterService();
                ok = service.insert(dto);
            }finally {
                out.close();
                reader.close();
            }

            if(ok){
                Utils.outResult(resp,Result.success());
            }else{
                Utils.outResult(resp, Result.fail(Result.ERR_CODE_SYS, "系统升级中......"));
            }

        }catch (Exception e){
            Utils.outResult(resp, Result.fail(Result.ERR_CODE_REPET, "账户已存在，请重试"));
        }



    }
}
