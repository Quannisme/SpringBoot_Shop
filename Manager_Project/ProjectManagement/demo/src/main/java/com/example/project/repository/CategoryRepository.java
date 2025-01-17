package com.example.project.repository;

import com.example.project.entity.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category , Integer> {
    List<Category>findAllByIdCategory(int category);
}
