package com.example.demo2_sp.entity.user;

public class User_Password_Login {
    private String phone;
    private String user_password;


    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User_Login{" +
                "phone='" + phone + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';
    }

    public User_Password_Login(String user_password, String phone) {
        this.user_password = user_password;
        this.phone = phone;
    }
}
