package com.example.demo2_sp.entity.user;


import jakarta.persistence.*;

// 确保表名正确
@Entity
@Table(name = "userinfo")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UID; // 主键字段

    private String user_name;
    private String phone;
    private String id_card;
    private String user_password;


    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    @Override
    public String toString() {
        return "User{" +
                "UID=" + UID +
                ", user_name='" + user_name + '\'' +
                ", phone='" + phone + '\'' +
                ", id_card='" + id_card + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public User() {
    }

    public User(String user_name, String phone, String id_card, String user_password) {
        this.user_name = user_name;
        this.phone = phone;
        this.id_card = id_card;
        this.user_password = user_password;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }
}
