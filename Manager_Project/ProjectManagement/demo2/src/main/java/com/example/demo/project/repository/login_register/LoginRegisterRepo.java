package com.example.demo.project.repository.login_register;

import com.example.demo.project.entity.login_register.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRegisterRepo extends JpaRepository<User, Integer> {
    User findUserByUserName(String name);
}
