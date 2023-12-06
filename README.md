# CafeXpress REST API

[Project Description]

## Overview

This project is a backend application for managing users, categories, items, advertisements, carts, and orders. It provides functionalities for user authentication, category management, item management, greeting messages, advertisement handling, cart operations, and order processing.

## Table of Contents
1. Project Structure
2. Entities
3. Controllers
4. Services
5. DTOs (Data Transfer Objects)
6. Exception Handling
7. API Endpoints
8. How to Run
9. Dependencies

## Project Structure
The project is structured into different packages to maintain a modular and organized codebase:
- com.example.project.entity: Contains entity classes representing database tables.
- com.example.project.controller: Includes controllers handling HTTP requests and defining API endpoints.
- com.example.project.service: Contains service classes that handle business logic and interact with repositories.
- com.example.project.dto: Contains Data Transfer Objects used to transfer data between controllers and services.
- com.example.project.exception: Holds custom exception classes for handling specific scenarios.
- com.example.project.repository: Includes repositories for interacting with the database.
- com.example.project.util: Contains utility classes.
- com.example.project.mapper: Contains mapper classes.
- com.example.project.enum: Contains enum classes.
- com.example.project.config: Contains configration classes.

## Entities
1. User: Represents user information including authentication details and orders.
2. Category: Represents product categories.
3. Item: Represents individual items with details such as name, description, and price.
4. Advertisement: Represents advertisements with a description and an associated image.
5. Cart: Represents a user's shopping cart containing cart items.
6. CartItem: Represents an item within a user's cart.
7. Order: Represents an order placed by a user.
8. OrderItem: Represents an item within an order.

## Controllers
1. AuthController: Manages user registration, login, and image retrieval.
2. CategoryController: Handles CRUD operations for categories and category images.
3. ItemController: Manages CRUD operations for items, including best items and item images.
4. GreetingController: Provides an endpoint to retrieve greeting messages.
5. AdvController: Handles CRUD operations for advertisements and advertisement images.
6. CartController: Manages operations related to user carts and cart items.
7. OrderController: Manages order confirmation and retrieval of all orders.

## Services
1. AuthService: Handles user registration and login logic.
2. CategoryService: Provides operations for managing categories and category images.
3. ItemService: Manages item-related operations, including best items and item images.
4. GreetingService: Retrieves greeting messages.
5. AdvService: Handles advertisement-related operations.
6. CartService: Manages cart-related operations, including cart creation and item addition/removal.
7. OrderService: Handles order confirmation and retrieval of all orders.

## DTOs
1. RegisterUserRequestDto: Data Transfer Object for user registration.
2. LoginUserRequestDto: Data Transfer Object for user login.
3. CategoryRequestDto: Data Transfer Object for creating or updating categories.
4. ItemRequestDto: Data Transfer Object for creating or updating items.
5. AdvRequestDto: Data Transfer Object for creating or updating advertisements.
6. AddToCartRequestDto: Data Transfer Object for adding items to the cart.

## Exception Handling
Custom exception classes in the com.example.project.exception package handle specific error scenarios, such as duplicate entries and bad requests.

## API Endpoints
- /api/version/auth/register: POST endpoint for user registration.
- /api/version/auth/login: POST endpoint for user login.
- /api/version/category: GET endpoint to retrieve all categories.
- /api/version/category/{id}: GET endpoint to retrieve a category by ID.
- /api/version/category/{id}: DELETE endpoint to delete a category by ID.
- /api/version/category: POST endpoint to create a new category.
- /api/version/items: GET endpoint to retrieve all items.
- /api/version/items/best: GET endpoint to retrieve best items.
- /api/version/items/{itemId}: GET endpoint to retrieve an item by ID.
- /api/version/items/{id}: DELETE endpoint to delete an item by ID.
- /api/version/items/{id}: PUT endpoint to update the best value of an item by ID.
- /api/version/items/category/{categoryId}: GET endpoint to retrieve items by category ID.
- /api/version/items: POST endpoint to create a new item.
- /api/version/greeting: GET endpoint to retrieve greeting messages.
- /api/version/adv: GET endpoint to retrieve all advertisements.
- /api/version/adv: POST endpoint to create a new advertisement.
- /api/version/cart/{userId}: GET endpoint to retrieve a user's cart by user ID.
- /api/version/cart/{userId}: POST endpoint to create a new cart for a user.
- /api/version/cart/addItem: POST endpoint to add an item to the user's cart.
- /api/version/cart/{userId}/removeItem/{itemId}: DELETE endpoint to remove an item from the user's cart.
- /api/version/order/confirm: POST endpoint to confirm an order.
- /api/version/order/all: GET endpoint to retrieve all orders.

## How to Run
1. Clone the repository.
2. Configure the database connection in the application.properties file.
3. Run the application using your preferred IDE or build tools.
4. Access the API through the specified endpoints.

## Dependencies
- Spring Boot: Provides a framework for building Java-based applications.
- Spring Data JPA: Simplifies database access using JPA.
- Hibernate: ORM (Object-Relational Mapping) framework.
- Postgres: Postgres database for development and testing.
- Spring Web: Enables building web applications with Spring.
- Lombok: Reduces boilerplate code in Java.
- Spring Validation: Provides validation support in Spring applications.and ....more dependancies
   
