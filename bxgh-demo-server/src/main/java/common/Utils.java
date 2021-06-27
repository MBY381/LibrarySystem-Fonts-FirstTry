package edu.jd.xyt.common;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
public class Utils {
    private static final String MYBATIS_CONFIG="mybatis.xml";
    //在类生成之时就引用buildFactory直接创建好了
    private static final SqlSessionFactory FACTORY=buildFactory();
    //创建这个sql会话工厂，其实就是把“mybatis.xml”文件里定义的sql数据库拿过来了
    //会话工厂对应的是整个数据库，应该使用static,有点大，各个对象应该共用一份，不要重复创建
    public static SqlSessionFactory buildFactory(){
        try {
            InputStream in = Resources.getResourceAsStream(MYBATIS_CONFIG);
            //创建语句
            return new SqlSessionFactoryBuilder().build(in);
        } catch (Exception e) {
            //这里要是直接抛出Exception，语法不通过会报错，所以先把它转化成Runtime error，避开语法检查
            throw new RuntimeException("创建SqlSessionFactory失败！",e);
        }
    }
    //获取sql工厂的办法：
    public static SqlSessionFactory getFactory(){
        return FACTORY;
    }
    public static SqlSession openSession(){
        return FACTORY.openSession();
    }
    public  static  void outJson(HttpServletResponse resp,Object obj)throws IOException{
        resp.setContentType("application/json;charset=UTF-8");//设置字符编码
        PrintWriter out = resp.getWriter();//获取向客户端发送字符信息流对象
        String jsonString= JSON.toJSONString(obj);
        out.println(jsonString);
        out.flush();
        out.close();
    }

    public  static  void outResult(HttpServletResponse resp,Result result) throws IOException {
        outJson(resp,result);
    }
}