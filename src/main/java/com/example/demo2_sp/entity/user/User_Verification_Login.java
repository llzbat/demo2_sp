package com.example.demo2_sp.entity.user;

public class User_Verification_Login {

    private String phone;
    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "User_Verification_Login{" +
                "phone='" + phone + '\'' +
                '}';
    }

    public User_Verification_Login() {
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User_Verification_Login(String phone) {
        this.phone = phone;
    }


}
