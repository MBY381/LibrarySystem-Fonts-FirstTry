package edu.jd.xyt.common;

public class CurrentUser {

    public static final String SESSION_ATTR_NAME="CurrentUser";//session属性名字

    public static final String AVATAR_DIR = "D:/touxiang";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String userId;
    private String userName;
    private String avatar;//头像地址

    public CurrentUser(String userId) {
        this.userId = userId;
    }

    public CurrentUser() {
    }

    public CurrentUser(String userId, String userName, String avatar) {
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
    }



}
