package com.example.project.service.size;

import com.example.project.entity.product.Size;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SizeService {
    Optional<Size> findById(int id);
    Size findByIdd(int id);
}
