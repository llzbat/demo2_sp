package com.example.demo2_sp.entity.UserType;


import jakarta.persistence.*;

@Entity
@Table(name = "usertype")
public class UserType {
    @Id
    private int UID;

    private int Type;

    public UserType() {

    }


    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public UserType(int UID, int type) {
        this.UID = UID;
        Type = type;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }



    @Override
    public String toString() {
        return "UserType{" +
                "UID=" + UID +
                ", Type=" + Type +
                '}';
    }
}
