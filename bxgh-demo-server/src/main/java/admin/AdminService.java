package edu.jd.xyt.admin;

import edu.jd.xyt.common.Utils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AdminService {

    public List<Admin> getAdminList() {
        SqlSession sess = Utils.openSession();
    try{
           //接口名.class，这里是把接口给实例化了，然后扔进getMapper里
            AdminDao dao=sess.getMapper(AdminDao.class);
            //或许可以这样理解，dao拥有这个interface有的一切东西，只不过接口里是空的，而“dao”里头装满了?
            List<Admin> AdminList=dao.findAdminList();
            sess.commit();//??commit??这个sess不是用来拿的吗
            return AdminList;
    }
    catch(Exception e){
            sess.rollback();
            throw new RuntimeException("查询管理员信息失败！",e);
    }
    finally {
            sess.close();
    }
    }
}
