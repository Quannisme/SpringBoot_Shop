package com.example.finalpm.dto;

import com.sun.istack.NotNull;
import javax.validation.constraints.Pattern;

public class UserDto {

    private int id;
    @NotNull(message ="khong duoc bo trong")
    private String name;
    private String userName;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private int phone;
}
