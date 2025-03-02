package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/p")
    public Product createProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // You can also add PUT and DELETE methods for full CRUD operations.
}
