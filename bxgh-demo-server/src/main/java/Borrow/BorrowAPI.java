package edu.jd.xyt.Borrow;

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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/bo")
public class BorrowAPI extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BorrowDto dto = null;
        try{
            req.setCharacterEncoding("UTF-8");

            Reader reader = req.getReader();
            CharArrayWriter out = new CharArrayWriter();//该输出流将数据输出到一个字符数组中
            boolean ok ;
            try {
                char[] charBuff = new char[1024];
                int len = -1;//每次从输入流实际读取的字符数

                while ((len = reader.read(charBuff)) != -1) {
                    out.write(charBuff, 0, len);
                }
                out.flush();
                String json = out.toString();
               dto = JSON.parseObject(json, BorrowDto.class);
                SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );//时间格式转换
                dto.borrow_time=sdf.format(new Date());
                //System.out.println(dto.borrow_time); System.out.println(dto.getBorrow_id());System.out.println(dto.getB_name());
                ok = new BorrowService().checkLogin(dto);
                 System.out.println(ok);
            }finally {
                out.close();
                reader.close();
            }

            if (ok) {//登录成功
                new BorrowService().insertborrow(dto);
                HttpSession session = req.getSession();
                session.setAttribute(CurrentUser.SESSION_ATTR_NAME,new CurrentUser(dto.getC_id()));
                System.out.println("currentUserlogin->"+req.getSession().getAttribute(CurrentUser.SESSION_ATTR_NAME));
                Utils.outResult(resp, Result.success());
            } else {
                Utils.outResult(resp, Result.fail(Result.ERR_CODE_BUSINESS, "ISBN号或者学号错误！"));
            }

        }
        catch (Exception e){
            Utils.outResult(resp,Result.fail(Result.ERR_CODE_SYS, "系统升级中......！"));
        }







    }
}
