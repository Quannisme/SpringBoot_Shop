package com.example.demo.project.service.login_register.inter;

import com.example.demo.project.entity.login_register.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginRegisterService {
    void create(User userDto);
    void update(User user);
    User findByUser(String user);
    User findById(int id);
}
