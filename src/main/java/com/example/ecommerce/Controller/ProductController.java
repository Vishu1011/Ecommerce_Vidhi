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

    // PUT: Update an existing product
    @PutMapping("/p/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);  // Assuming you have this method in your service
    }

    // DELETE: Delete a product by ID
    @DeleteMapping("/p/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);  // Assuming you have this method in your service

    }

    // PATCH: Partially update a product
    @PatchMapping("/p/{id}")
    public Product patchProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.patchProduct(id, productDTO); // Assuming you have this method in your service
    }
}