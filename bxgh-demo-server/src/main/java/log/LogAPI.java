package edu.jd.xyt.log;

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
import java.util.List;
import java.util.Map;

@WebServlet({
        "/log/LogList/insert",
        "/log/LogList",
        "/log/LogList/delete"
})
public class LogAPI extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path= req.getServletPath();
        if("/log/LogList".equals(path))
        {
            try{
                req.setCharacterEncoding("UTF-8");

                Reader reader = req.getReader();
                CharArrayWriter out = new CharArrayWriter();//该输出流将数据输出到一个字符数组中
                try {
                    char[] charBuff = new char[1024];
                    int len = -1;//每次从输入流实际读取的字符数
                    while ((len = reader.read(charBuff)) != -1) {
                        out.write(charBuff, 0, len);
                    }
                    out.flush();
                    String json = out.toString();
                    Log log = JSON.parseObject(json, Log.class);
                    LogService.updateLog(log);
                }finally {
                    out.close();
                    reader.close();
                }
            }
            catch (Exception e){
                Utils.outResult(resp,Result.fail(Result.ERR_CODE_SYS, "系统升级中......！"));
            }
        }
        else if("/log/LogList/insert".equals(path))
        {
            try{
                req.setCharacterEncoding("UTF-8");

                Reader reader = req.getReader();
                CharArrayWriter out = new CharArrayWriter();//该输出流将数据输出到一个字符数组中
                try {
                    char[] charBuff = new char[1024];
                    int len = -1;//每次从输入流实际读取的字符数
                    while ((len = reader.read(charBuff)) != -1) {
                        out.write(charBuff, 0, len);
                    }
                    out.flush();
                    String json = out.toString();
                    Log log = JSON.parseObject(json, Log.class);
                    LogService.insertLog(log);
                }finally {
                    out.close();
                    reader.close();
                }
            }
            catch (Exception e){
                e.printStackTrace();
                Utils.outResult(resp,Result.fail(Result.ERR_CODE_SYS, "系统升级中......！"));
            }
        }
        else if("/log/LogList/delete".equals(path))
        {
            try{
                req.setCharacterEncoding("UTF-8");

                Reader reader = req.getReader();
                CharArrayWriter out = new CharArrayWriter();//该输出流将数据输出到一个字符数组中
                try {
                    char[] charBuff = new char[1024];
                    int len = -1;//每次从输入流实际读取的字符数
                    while ((len = reader.read(charBuff)) != -1) {
                        out.write(charBuff, 0, len);
                    }
                    out.flush();
                    String json = out.toString();
                    Log log = JSON.parseObject(json, Log.class);
                    boolean tmp= LogService.deleteLog(log);
                }finally {
                    out.close();
                    reader.close();
                }
            }
            catch (Exception e){
                e.printStackTrace();
                Utils.outResult(resp,Result.fail(Result.ERR_CODE_SYS, "系统升级中......！"));
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建业务模型对象(服务对象),通过业务对象生成业务方法
        request.setCharacterEncoding("UTF-8");
        queryDto q=new queryDto();
        q.setParam(request.getParameter("search_id"));
        try{
            q.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
            q.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("↓");
        System.out.println(q.getParam());
        LogService LogService = new LogService();
        Map<String,Object> LogList = LogService.getLogList(q);
        //通过视图生成json
        //Utils.outJson(response,teaList);
        Utils.outResult(response, Result.success(LogList));
    }
}
