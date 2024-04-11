package com.example.demo.project.dto;

import com.sun.istack.NotNull;
import org.intellij.lang.annotations.Pattern;

public class UserDto {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String userName;
    @NotNull
//    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$" ,message = "Form khong dung dinh dang")

    private String email;
    @NotNull
    private String password;
    @NotNull
    private String phone;
    public UserDto() {
    }

    public UserDto(int id, String name, String userName, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
