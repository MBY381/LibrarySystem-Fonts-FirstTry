package edu.jd.xyt.home;

import edu.jd.xyt.common.CurrentUser;
import edu.jd.xyt.common.Result;
import edu.jd.xyt.common.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.UUID;

@MultipartConfig
@WebServlet({
        "/current-user",//获取当前用户信息
        "/avatar"//获取头像
})
public class HomeAPI extends HttpServlet {

    //该方法在Sevelet初始化时执行，仅执行一次
    @Override
    public void init() throws ServletException {
        File dir = new File(CurrentUser.AVATAR_DIR);//表示文件或目录的路径
        if(!dir.exists()){
            dir.mkdirs();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomeService service = new HomeService();
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();

        if("/avatar".equals(path)){
            Part part = request.getPart("avatar");

            InputStream in = part.getInputStream();

            String name = UUID.randomUUID().toString();
            String submitedFilename = part.getSubmittedFileName();
            String suffix = submitedFilename.substring(submitedFilename.lastIndexOf("."));
            String filename = name+suffix;
            OutputStream out = new  FileOutputStream(CurrentUser.AVATAR_DIR+"/"+filename);

            byte[] b = new byte[10240];
            int len =-1;

            while ((len=in.read(b))!=-1){
                out.write(b,0,len);
            }
            out.flush();
            out.close();
            in.close();

            CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute(CurrentUser.SESSION_ATTR_NAME);

            service.setAvatar(currentUser,filename);

            Utils.outResult(response,Result.success());

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        HomeService service = new HomeService();
        if("/current-user".equals(path)){
            System.out.println("session?");
            System.out.println(request.getSession().getId());
            CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute(CurrentUser.SESSION_ATTR_NAME);
            System.out.println(currentUser);
            service.loadCurrentUser(currentUser);
            System.out.println("????");
            Utils.outResult(response, Result.success(currentUser));
        }

        if ("/avatar".equals(path)) {

            String filename = request.getParameter("filename");

            System.out.println(filename);
            InputStream in = new FileInputStream(CurrentUser.AVATAR_DIR+"/"+filename);

            OutputStream out = response.getOutputStream();

            byte[] b = new byte[10240];
            int len =-1;

            while ((len=in.read(b))!=-1){
                out.write(b,0,len);
            }
            out.flush();
            out.close();
            in.close();
        }

    }
}
