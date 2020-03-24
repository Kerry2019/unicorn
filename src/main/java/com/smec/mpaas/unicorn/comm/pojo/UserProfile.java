package com.smec.mpaas.unicorn.comm.pojo;

import java.util.Map;

public class UserProfile {
    public static final String ANONYMOUS_USER ="anonymous";
    public static final UserProfile ANONYMOUS_OBJ = new UserProfile(ANONYMOUS_USER,true);

    /**
     * 用户唯一编号
     */
    private String uid;
    /**
     * 用户名，保存显示名称
     */
    private String userName;
    /**
     * 用户token
     */
    private String token;
    /**
     * 额外属性
     */
    private Map<String, String> attributes;
    /**
     * 标识该用户是否是匿名登录(也就是未登录)
     */
    private boolean anonymous = true;


    public UserProfile() {

    }

    public UserProfile(String uid,boolean anonymous) {
        this.uid = uid;
        this.anonymous=anonymous;
    }

    public UserProfile(String uid,String userName,String token,boolean anonymous) {
        this.uid = uid;
        this.userName=userName;
        this.token=token;
        this.anonymous=anonymous;
    }

    public static String getAnonymousUser() {
        return ANONYMOUS_USER;
    }

    public static UserProfile getAnonymousObj() {
        return ANONYMOUS_OBJ;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
