package com.example.project.repository;

import com.example.project.entity.product.Product;
import com.example.project.entity.product.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size , Integer> {
    Size findSizeById(int id);
}
