package com.example.demo.project.util;

import com.example.demo.project.dto.UserDto;
import com.example.demo.project.service.login_register.inter.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private LoginRegisterService loginRegisterService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(!(target instanceof UserDto))
        {
            return;
        }
    }
}
