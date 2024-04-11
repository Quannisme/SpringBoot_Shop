package com.example.demo.project.service.userProduct.inter;

import com.example.demo.project.entity.UserProduct;
import com.example.demo.project.entity.login_register.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserProductService {
    void save(UserProduct userProduct);

    List<UserProduct>findAllByStatus();
    List<UserProduct>findAllByTrue(User user);
    void deleteById(int id);
}
