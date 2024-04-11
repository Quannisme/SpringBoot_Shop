package com.example.project.service.product.impl;

import com.example.project.entity.product.Product;
import com.example.project.repository.ProductRepository;
import com.example.project.service.product.inter.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategory(int category) {
        return productRepository.findProductsByCategoryIdCategory(category);
    }
    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByTitle(String index) {
        return productRepository.findProductsByTitleContainingIgnoreCase(index);
    }

    @Override
    public List<Product> findCategory(int id) {
        return productRepository.findProductsByCategoryIdCategory(id);
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(int id) {
        productRepository.deleteById(id);
    }
}
