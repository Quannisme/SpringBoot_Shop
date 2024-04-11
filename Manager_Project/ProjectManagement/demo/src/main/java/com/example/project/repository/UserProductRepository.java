package com.example.project.repository;

import com.example.project.entity.UserProduct;
import com.example.project.entity.login_register.User;
import com.example.project.entity.product.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProductRepository extends JpaRepository<UserProduct , Integer> {
    List<UserProduct>findAllByStatusIsFalse();
    List<UserProduct>findAllByStatusIsTrue();
    List<UserProduct>findAllByUserAndAndStatusIsTrue(User user);
}
