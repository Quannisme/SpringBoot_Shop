package com.example.project.repository;

import com.example.project.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product , Integer> {
    Optional<Product> findById(Integer id);
    List<Product>findProductsByTitleContainingIgnoreCase(String index);
    List<Product>findProductsByCategoryIdCategory(int id);
}
