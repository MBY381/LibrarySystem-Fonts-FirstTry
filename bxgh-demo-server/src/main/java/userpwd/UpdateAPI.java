package edu.jd.xyt.userpwd;

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
        "/updatepwd"//直接修改密码
}
)
public class UpdateAPI extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        UpdateDto dto1=null;
        String json;
        try (Reader reader = request.getReader(); CharArrayWriter out = new CharArrayWriter()) {
            char[] charBuff = new char[100];
            int len = -1;
            while ((len = reader.read(charBuff)) != -1) {
                out.write(charBuff, 0, len);
            }
            out.flush();
            json = out.toString();
            dto1 = JSON.parseObject(json,UpdateDto.class);
        }
        if (path.equals("/updatepwd")) {
            System.out.println(json);
            if (dto1.getC_pwd1().equals(dto1.getC_pwd2()) &&dto1.getC_pwd1().length()>=6) {
                try{
                    UpdateService.updatepwd(dto1);
                    Utils.outJson(response, Result.success());
                }catch (Exception e){
                    e.printStackTrace();
                    Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, e.getMessage()));
                }
            }else if(!dto1.getC_pwd1().equals(dto1.getC_pwd2())){
                Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, "密码不一致，请重试！"));
            }else if (dto1.getC_pwd1().length()<6){
                Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, "密码至少长度为6！"));
            }
        }
    }
}
