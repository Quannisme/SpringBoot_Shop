package com.example.project.repository.login_register;

import com.example.project.entity.login_register.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginRegisterRepo extends JpaRepository<User, Integer> {
    User findUserByUserName(String name);
}
