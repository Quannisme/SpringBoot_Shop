package com.example.project.controller.Product;

import com.example.project.entity.product.Product;
import com.example.project.service.product.inter.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class RestControllerProduct {
    @Autowired
    private ProductService productService;
    @GetMapping("/searchProduct")
    public ResponseEntity<?> getFindByTitle(@RequestParam("index") String index)
    {
        return new ResponseEntity<>(productService.findByTitle(index), HttpStatus.OK);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Product>>getFindAll(Model model){
        List<Product>list=productService.findAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
