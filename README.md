# E-Commerce Shipping Service Application

This project is a Spring Boot-based microservice application designed for managing product tracking information and sending email notifications via Kafka. It integrates with a PostgreSQL database for storing product information and uses Kafka to send messages related to product tracking. The system exposes endpoints to perform CRUD operations on product data, and it sends emails when product tracking information is updated.

## Project Structure

### 1. **DTO (Data Transfer Object)**
- **ProductDTO**: Represents the data structure for product information that will be transferred between different layers of the application (such as from the API to the service). It contains product details like `productId`, `productName`, `productType`, `price`, `trackingId`, and `estimatedDeliveryDate`.

### 2. **Entity (Product Entity)**
- **Product Entity**: Maps the product data to the PostgreSQL database. It represents a product's details like name, type, price, tracking ID, and estimated delivery date.

### 3. **Repository**
- **ProductRepository**: A Spring Data JPA repository interface for performing CRUD operations on the `Product` entity in the PostgreSQL database.

### 4. **Service**
- **ProductService**: Contains the business logic for managing products. This includes saving product information, sending product tracking notifications via Kafka, and sending emails to users about product tracking.

### 5. **Controller**
- **ProductController**: Exposes REST API endpoints for interacting with the product data. It provides methods to create products, fetch product details by ID, and potentially update or delete products in the future.

## Kafka Configuration

- The application is configured to use **Kafka** for asynchronous messaging. When a product's tracking details are updated, Kafka is used to send a message containing the product's tracking information.
- Kafka is also used to trigger email notifications to users about their product's tracking status.

## Features

- **CRUD Operations**:
    - `POST /api/products`: Create a new product with tracking details.
    - `GET /api/products/{id}`: Retrieve a product's details by its ID.
    - You can extend the functionality to include `PUT` (for updating products) and `DELETE` (for removing products).

- **Email Notifications**:
    - After a product's details are saved, the system sends an email to the specified recipient (e.g., customer) about the product's tracking information.

- **Kafka Messaging**:
    - A Kafka producer sends messages about product tracking updates, which can be consumed by other services or systems.

