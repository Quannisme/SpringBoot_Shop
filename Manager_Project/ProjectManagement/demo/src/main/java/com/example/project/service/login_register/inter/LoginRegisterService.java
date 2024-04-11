package com.example.project.service.login_register.inter;

import com.example.project.entity.login_register.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginRegisterService {
    void create(User userDto);
    void update(User user);
    User findByUser(String user);
    User findById(int id);
}
