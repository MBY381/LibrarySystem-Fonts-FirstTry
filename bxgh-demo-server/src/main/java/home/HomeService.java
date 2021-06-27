package edu.jd.xyt.home;

import edu.jd.xyt.common.CurrentUser;
import edu.jd.xyt.common.Utils;


import org.apache.ibatis.session.SqlSession;



public class HomeService {
    public void loadCurrentUser(CurrentUser currentUser) {
        SqlSession sess = Utils.openSession();
        try{
            HomeDao dao = sess.getMapper(HomeDao.class);
            Admin admin = dao.findAdminById(currentUser.getUserId());
            sess.commit();
            currentUser.setUserName(admin.getA_name());
            currentUser.setAvatar(admin.getA_avatar_path());




        }catch(Exception e){
            e.printStackTrace();
            sess.rollback();
            throw new RuntimeException("加载用户信息失败！",e);
        }finally{
            sess.close();
        }
    }

    public void setAvatar(CurrentUser currentUser, String filename) {
        SqlSession sess = Utils.openSession();
        try{
            HomeDao dao = sess.getMapper(HomeDao.class);
            dao.updateAvatar(currentUser.getUserId(),filename);
            sess.commit();


        }catch(Exception e){
            sess.rollback();
            throw new RuntimeException("更新头像失败！",e);
        }finally{
            sess.close();
        }
    }
}
