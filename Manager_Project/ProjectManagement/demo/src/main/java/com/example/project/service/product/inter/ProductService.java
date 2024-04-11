package com.example.project.service.product.inter;

import com.example.project.entity.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product>findAll();
    Product findProductById(int id);
    List<Product>findByCategory(int category);
    Optional<Product> findById(int id);
    List<Product>findByTitle(String index);
    List<Product>findCategory(int id);
    void remove(int id);
}
