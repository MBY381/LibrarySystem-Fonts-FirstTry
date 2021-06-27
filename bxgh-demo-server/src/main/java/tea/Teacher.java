package edu.jd.xyt.tea;

import java.util.Date;

public class Teacher {
    private String t_id;
    private  String t_name;
    private Integer t_sex;
    private Date t_birth;
    private Integer t_edu;
    private String t_title;
    private String t_remark;
    private String t_avatar;
    private Integer t_status;

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public Integer getT_sex() {
        return t_sex;
    }

    public void setT_sex(Integer t_sex) {
        this.t_sex = t_sex;
    }

    public Date getT_birth() {
        return t_birth;
    }

    public void setT_birth(Date t_birth) {
        this.t_birth = t_birth;
    }

    public Integer getT_edu() {
        return t_edu;
    }

    public void setT_edu(Integer t_edu) {
        this.t_edu = t_edu;
    }

    public String getT_title() {
        return t_title;
    }

    public void setT_title(String t_title) {
        this.t_title = t_title;
    }

    public String getT_remark() {
        return t_remark;
    }

    public void setT_remark(String t_remark) {
        this.t_remark = t_remark;
    }

    public String getT_avatar() {
        return t_avatar;
    }

    public void setT_avatar(String t_avatar) {
        this.t_avatar = t_avatar;
    }

    public Integer getT_status() {
        return t_status;
    }

    public void setT_status(Integer t_status) {
        this.t_status = t_status;
    }
}
