package edu.jd.xyt.log;


import edu.jd.xyt.common.PageParam;

public class Log extends PageParam {
    Integer c_id;
    String c_key;
    String c_name;
    String c_email;
    String c_avatar_path;

    public String getC_avatar_path() {
        return c_avatar_path;
    }

    public void setC_avatar_path(String c_avatar_path) {
        this.c_avatar_path = c_avatar_path;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }


    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getC_key() {
        return c_key;
    }
    public void setC_key(String c_key) {
        this.c_key = c_key;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public Integer getBorrow_num() {
        return borrow_num;
    }

    public void setBorrow_num(Integer borrow_num) {
        this.borrow_num = borrow_num;
    }

    Integer borrow_num;

}
