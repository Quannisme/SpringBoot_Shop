package com.example.demo.project.service.size;

import com.example.demo.project.entity.product.Size;
import com.example.demo.project.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService{
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public Optional findById(int id) {
       return sizeRepository.findById(id);
    }

    @Override
    public Size findByIdd(int id) {
        return sizeRepository.findSizeById(id);
    }
}
