package com.example.demo.project.service.user.inter;

import com.example.demo.project.entity.login_register.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUserName(String userName);
}
