package com.example.demo.project.repository;

import com.example.demo.project.entity.product.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    Size findSizeById(int id);
}
