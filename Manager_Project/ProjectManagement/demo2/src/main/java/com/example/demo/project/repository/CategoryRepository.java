package com.example.demo.project.repository;

import com.example.demo.project.entity.product.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category>findAllByIdCategory(int category);
}
