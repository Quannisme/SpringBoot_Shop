package com.example.demo.project.repository;

import com.example.demo.project.entity.UserProduct;
import com.example.demo.project.entity.login_register.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProductRepository extends JpaRepository<UserProduct, Integer> {
    List<UserProduct>findAllByStatusIsFalse();
    List<UserProduct>findAllByStatusIsTrue();
    List<UserProduct>findAllByUserAndAndStatusIsTrue(User user);
}
