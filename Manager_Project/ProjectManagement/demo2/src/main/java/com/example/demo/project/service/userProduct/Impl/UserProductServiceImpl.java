package com.example.demo.project.service.userProduct.Impl;

import com.example.demo.project.entity.UserProduct;
import com.example.demo.project.entity.login_register.User;
import com.example.demo.project.repository.UserProductRepository;
import com.example.demo.project.service.userProduct.inter.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProductServiceImpl implements UserProductService {
    @Autowired
    private UserProductRepository userProductRepository;
    @Override
    public void save(UserProduct userProduct) {
        userProductRepository.save(userProduct);
    }

    @Override
    public List<UserProduct> findAllByStatus() {
        return userProductRepository.findAllByStatusIsFalse();
    }

    @Override
    public List<UserProduct> findAllByTrue(User user) {
        return userProductRepository.findAllByUserAndAndStatusIsTrue(user);
    }

    @Override
    public void deleteById(int id) {
        userProductRepository.deleteById(id);
    }
}
