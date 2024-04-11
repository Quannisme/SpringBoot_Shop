package com.example.project.repository;

import com.example.project.entity.login_register.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Integer> {
    User findUserByUserName(String userName);
}
