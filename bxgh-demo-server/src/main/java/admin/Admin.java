package edu.jd.xyt.admin;

import java.util.Date;

public class Admin {
    private Integer a_id;
    private  String key;

    public String getA_avatar_path() {
        return a_avatar_path;
    }

    public void setA_avatar_path(String a_avatar_path) {
        this.a_avatar_path = a_avatar_path;
    }

    private  String a_name;
    private String a_avatar_path;
    public Integer getA_id() {
        return a_id;
    }

    public String getKey() {
        return key;
    }

    public String getA_name() {
        return a_name;
    }

    public Admin(Integer a_id, String key, String a_name) {
        this.a_id = a_id;
        this.key = key;
        this.a_name = a_name;
    }

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }
}
