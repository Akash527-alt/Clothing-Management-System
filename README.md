# 🧥 Clothing Shop Management System

A Full-Stack Clothing Shop Management System built using **Java 21** and **Spring Boot 4** to manage products, inventory, sales, and generate analytical reports.

This project focuses on clean architecture, optimized database queries, and backend performance handling.

---

## 🌐 Live Demo

🔗 https://clothing-management-system.onrender.com/ClothingShop/products

⚠️ Note: Since the project is hosted on Render free tier, it may take **1–2 minutes to load** on first visit due to server cold start.

---

## 🚀 Project Overview

This system helps manage:

- Product inventory
- Sales records
- Profit calculation
- Stock tracking
- Category-wise reporting
- Old stock detection

The main goal of this project was not just CRUD operations, but implementing optimized reporting queries and maintaining a clean backend structure.

---

## 🛠 Tech Stack

### 🔹 Backend
- Java 21
- Spring Boot 4
- Spring Data JPA
- Hibernate
- Lombok
- PostgreSQL (Neon Cloud DB)

### 🔹 Frontend
- Thymeleaf
- HTML5
- CSS3
- Bootstrap 
- JavaScript (Vanilla JS)

### 🔹 Tools
- Git
- GitHub
- Render (Deployment)

---

## 🏗 Architecture

The project follows a layered architecture:

Controller Layer  
→ Handles HTTP requests and REST APIs

Service Layer  
→ Contains business logic and calculations

Repository Layer  
→ Database interaction using Spring Data JPA

Entity Layer  
→ Represents database tables

DTO Layer  
→ Used for optimized data transfer and custom projections

This structure keeps the code organized, scalable, and easy to maintain.

---

## 🛍 Features

### Product Management
- Add / Update / Delete Products
- Manage Categories
- Track Purchase Price & Selling Price
- Stock Quantity Management

### Sales Management
- Create Sales Records
- Automatic Total Amount Calculation
- Automatic Profit Calculation
- Track Sales Date

### Reports & Analytics
- Last N Sales (Optimized Fetching)
- Category-wise Sales Summary
- Category-wise Profit Summary
- Quantity Sold per Category
- Old Stock Detection (Based on months)
- Monthly & Last 30 Days Reporting Logic

---

## ⚡ Performance Optimizations

In this project, We implemented:

- Custom JPQL aggregation queries
- GROUP BY with SUM() calculations
- DTO-based projections instead of full entity loading
- Limited record fetching to avoid large payloads
- Stream API for data transformation
- Consideration for pagination in large datasets

The focus was to avoid unnecessary database load and improve response efficiency.

---

## 📊 Sample API Endpoints

### 1️⃣ Last N Sales
GET /api/reports/last-n-sales?n=10

Returns latest N sales ordered by date.

---

### 2️⃣ Category-wise Summary

Returns:
- Category
- Total Sales
- Total Profit
- Total Quantity Sold

Uses optimized JPQL aggregation query.

---

### 3️⃣ Old Stock Report
GET /api/reports/old-stock?months=6

Fetches products that have not been sold for the given number of months.

---

## 📂 Project Structure
    src/main/java
    ├── controller
    ├── service
    ├── repository
    ├── entity
    ├── dto
    
    src/main/resources
    ├── templates
    ├── static
    ├── application-example.properties
    ├── application-prod.properties
    

---

## ⚙️ How To Run Locally

### 1️⃣ Clone Repository
git clone https://github.com/Akash527-alt/Clothing-Management-System.git


### 2️⃣ Configure Database

Copy `application-example.properties`
and rename it to `application.properties`, then update with your credentials.

spring.datasource.url=jdbc:postgresql://localhost:5432/your_database  
spring.datasource.username=your_username    
spring.datasource.password=your_password


server.port=${PORT:8080}



### 3️⃣ Run Application
mvn spring-boot:run


Application will start at:


http://localhost:8080


---

## 🔮 Future Improvements

- Add Role-Based Authentication (Spring Security)
- Add Pagination & Sorting
- Add Dashboard with Charts
- Add Swagger Documentation
- Add Unit & Integration Testing
- Improve UI design

---

## 👨‍💻 Authors

**Akash Prajapati**  
Bachelor of Computer Science (Expected 2027)  
GitHub: https://github.com/Akash527-alt

**Darshan Parekh**  
Bachelor of Computer Science (Expected 2027)  
GitHub: https://github.com/darshan2456

---

## 📄 License

This project is developed for educational and learning purposes.