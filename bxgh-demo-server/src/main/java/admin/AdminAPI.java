package edu.jd.xyt.admin;

import edu.jd.xyt.common.Result;
import edu.jd.xyt.common.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/AdminList")
public class AdminAPI extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建业务模型对象(服务对象),通过业务对象生成业务方法
        AdminService AdminService = new AdminService();
        List<Admin> AdminList = AdminService.getAdminList();
        //通过视图生成json
        Utils.outResult(response, Result.success(AdminList));
    }
}
