package com.example.project.service.login_register.impl;

import com.example.project.entity.login_register.User;
import com.example.project.repository.login_register.LoginRegisterRepo;
import com.example.project.service.login_register.inter.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {
    @Autowired
    private LoginRegisterRepo loginRegisterRepo;

    @Override
    public void create(User userDto) {
        loginRegisterRepo.save(userDto);
    }

    @Override
    public void update(User user) {
        loginRegisterRepo.save(user);
    }

    @Override
    public User findByUser(String user) {
        return loginRegisterRepo.findUserByUserName(user);
    }

    @Override
    public User findById(int id) {
        return loginRegisterRepo.findById(id).orElse(null);
    }
}
