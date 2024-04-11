package com.example.project.service.userProduct.inter;

import com.example.project.entity.UserProduct;
import com.example.project.entity.login_register.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserProductService {
    void save(UserProduct userProduct);

    List<UserProduct>findAllByStatus();
    List<UserProduct>findAllByTrue(User user);
    void deleteById(int id);
}
