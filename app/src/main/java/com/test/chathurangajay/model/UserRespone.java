package com.test.chathurangajay.model;

public class UserRespone {
    String res_code;
    String res_desc;
    UserData user_data;

    public UserRespone(String res_code, String res_desc, UserData user_data) {
        this.res_code = res_code;
        this.res_desc = res_desc;
        this.user_data = user_data;
    }

    public String getRes_code() {
        return res_code;
    }

    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }

    public String getRes_desc() {
        return res_desc;
    }

    public void setRes_desc(String res_desc) {
        this.res_desc = res_desc;
    }

    public UserData getUser_data() {
        return user_data;
    }

    public void setUser_data(UserData user_data) {
        this.user_data = user_data;
    }
}
