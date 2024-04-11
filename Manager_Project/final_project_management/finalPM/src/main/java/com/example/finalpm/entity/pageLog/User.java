package com.example.finalpm.entity.pageLog;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;
    @Column(name = "user_name" , columnDefinition = "varchar(45)")
    private String name;
    @Column(name = "user_userName")
    private String userName;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_password" , columnDefinition = "varchar(45)")
    private String password;
    @Column(name = "user_fullName" , columnDefinition = "varchar(60)")
    private String fullName;
    @Column(name = "user_address")
    private String address;
    @Column(name = "user_phone")
    private int phone;
    public User() {
    }

    public User(int id, String name, String userName, String email, String password, String fullName, String address, int phone) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
