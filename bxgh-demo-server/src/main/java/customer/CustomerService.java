package edu.jd.xyt.customer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.jd.xyt.common.Utils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {

    public static void updateCustomer(Customer customer) {
        SqlSession sqlSession = Utils.openSession();

        try{
            CustomerDao dao = sqlSession.getMapper(CustomerDao.class);

            boolean tmp=dao.UpdateCustomer(customer);
            System.out.println(tmp);
            sqlSession.commit();

        }catch(Exception e){
            sqlSession.rollback();
            throw new RuntimeException(e);
        }finally{
            sqlSession.close();
        }
    }

    public static void insertCustomer(Customer customer) {

        SqlSession sqlSession = Utils.openSession();

        try{
            CustomerDao dao = sqlSession.getMapper(CustomerDao.class);
            boolean tmp=dao.InsertCustomer(customer);
            System.out.println(tmp);
            System.out.println("???");
            sqlSession.commit();
        }catch(Exception e){
            System.out.println("更新失败");
            e.printStackTrace();
            sqlSession.rollback();
            throw new RuntimeException(e);
        }finally{

            sqlSession.close();
        }
    }

    public static boolean deleteCustomer(Customer customer) {
        SqlSession sqlSession = Utils.openSession();
        boolean tmp=false;
        try{
            CustomerDao dao = sqlSession.getMapper(CustomerDao.class);
            tmp=dao.DeleteCustomer(customer);
            System.out.println(tmp);
            System.out.println("???");
            sqlSession.commit();
        }catch(Exception e){
            System.out.println("更新失败");
            e.printStackTrace();
            sqlSession.rollback();
            throw new RuntimeException(e);
        }finally{
            sqlSession.close();
        }
        return tmp;
    }


    public Map<String,Object> getCustomerList(queryDto q) {
        SqlSession sess = Utils.openSession();
        try {
            //接口名.class，这里是把接口给实例化了，然后扔进getMapper里
            CustomerDao dao = sess.getMapper(CustomerDao.class);
            //或许可以这样理解，dao拥有这个interface有的一切东西，只不过接口里是空的，而“dao”里头装满了?
            List<Customer> CustomerList;
            String param=q.getParam();
            PageHelper.startPage(q);
            if(param==null||param==""){
                CustomerList = dao.findCustomerList();
            }
            else{
                System.out.println(param);
                System.out.println("haha");
                CustomerList = dao.findCustomerList_by_data(q);
            }
            PageInfo<Customer> pageInfo= new PageInfo<Customer>(CustomerList);

            Map<String,Object> page=new HashMap<>();
            page.put("current", pageInfo.getPageNum());//当前页
            page.put("pageSize", pageInfo.getPageSize());//每页最大记录数
            page.put("total", pageInfo.getTotal());//每页最大记录数
            page.put("pages", pageInfo.getPages());//总页数
            page.put("size", pageInfo.getSize());//当前页实际记录数
            page.put("list",pageInfo.getList());
            page.put("first", 1);//每页最大记录数
            page.put("pre", pageInfo.getPrePage());
            page.put("next",pageInfo.getNextPage());
            page.put("last", pageInfo.getPages());


            sess.commit();//??commit??这个sess不是用来拿的吗
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            sess.rollback();
            throw new RuntimeException("查询管理员信息失败！", e);
        } finally {
            sess.close();
        }
    }

}
