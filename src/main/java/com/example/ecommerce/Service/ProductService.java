package com.example.ecommerce.Service;

import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.Optional;

/*
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Method to save a new product
    public Product saveProduct(ProductDTO productDTO) {
        // Convert ProductDTO to Product entity
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductType(productDTO.getProductType());
        product.setPrice(productDTO.getPrice());
        product.setTrackingId(productDTO.getTrackingId());
        product.setEstimatedDeliveryDate(productDTO.getEstimatedDeliveryDate());

        // Save the product to the database
        return productRepository.save(product);
    }

    // Method to get a product by its ID
    public Product getProductById(Long id) {
        // Find the product by ID, if not present, return null or throw exception
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null); // Can throw a custom exception if you prefer
    }

    // Method to update an existing product
    public Product updateProduct(Long id, ProductDTO productDTO) {
        // Find the product by its ID
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // Update fields with data from DTO
            product.setProductName(productDTO.getProductName());
            product.setProductType(productDTO.getProductType());
            product.setPrice(productDTO.getPrice());
            product.setTrackingId(productDTO.getTrackingId());
            product.setEstimatedDeliveryDate(productDTO.getEstimatedDeliveryDate());

            // Save and return the updated product
            return productRepository.save(product);
        } else {
            // Handle the case where the product does not exist (e.g., throw an exception)
            return null;
        }
    }

    // Method to delete a product by its ID
    public void deleteProduct(Long id) {
        // Check if the product exists before deleting it
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
        } else {
            // Handle the case where the product does not exist (e.g., throw an exception)
            throw new RuntimeException("Product not found for id: " + id);
        }
    }
}
*/

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private JavaMailSender mailSender;

    private static final String TOPIC = "product-tracking-topic";

    public Product saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setProductType(productDTO.getProductType());
        product.setPrice(productDTO.getPrice());
        product.setTrackingId(productDTO.getTrackingId());
        product.setEstimatedDeliveryDate(productDTO.getEstimatedDeliveryDate());

        Product savedProduct = productRepository.save(product);

        sendEmailNotification(savedProduct);
        sendProductTrackingMessage(savedProduct);

        return savedProduct;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void sendEmailNotification(Product product) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("vidhi776776@gmail.com"); // Set to a real recipient email
        message.setSubject("Product Tracking Information");
        message.setText("Product Name: " + product.getProductName() + "\n"
                + "Tracking ID: " + product.getTrackingId() + "\n"
                + "Estimated Delivery Date: " + product.getEstimatedDeliveryDate());
        mailSender.send(message);
    }

    public void sendProductTrackingMessage(Product product) {
        kafkaTemplate.send(TOPIC, "Product with ID " + product.getProductId() + " is being shipped. Tracking ID: " + product.getTrackingId());
    }
}
