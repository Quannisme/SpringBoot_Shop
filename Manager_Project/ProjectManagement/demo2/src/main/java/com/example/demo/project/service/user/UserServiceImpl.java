package com.example.demo.project.service.user;

import com.example.demo.project.entity.login_register.User;
import com.example.demo.project.repository.UserRepository;
import com.example.demo.project.service.user.inter.UserService;
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
