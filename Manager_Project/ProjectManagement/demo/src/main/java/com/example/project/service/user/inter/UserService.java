package com.example.project.service.user.inter;

import com.example.project.entity.login_register.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUserName(String userName);
}
