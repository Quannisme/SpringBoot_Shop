package com.example.demo.project.repository;

import com.example.demo.project.entity.login_register.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserName(String userName);
}
