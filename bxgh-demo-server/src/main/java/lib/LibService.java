package edu.jd.xyt.lib;

import edu.jd.xyt.common.Utils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class LibService {
    public List<Library> getLibraryList () {

        SqlSession sess = Utils.openSession();
        try {
            LibDao dao = sess.getMapper(LibDao.class);
            List<Library> libList =dao.findLibraryList();
            sess.commit();
            return libList;
        }catch (Exception e){
            sess.rollback();
            throw new RuntimeException("��ѯ������Ϣʧ�ܣ�",e);
        }finally {
            sess.close();
        }

    }
}
