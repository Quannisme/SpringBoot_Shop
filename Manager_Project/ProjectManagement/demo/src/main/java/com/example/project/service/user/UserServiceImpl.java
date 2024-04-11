package com.example.project.service.user;

import com.example.project.entity.login_register.User;
import com.example.project.repository.UserRepository;
import com.example.project.service.user.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }
}
