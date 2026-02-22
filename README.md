# 🧥 Clothing Shop Management System

A Full-Stack Clothing Shop Management System built using **Java 21** and **Spring Boot 4.0.1** to manage products, sales, inventory, and generate analytical reports with optimized database queries.

This project follows a clean layered architecture and focuses on performance-aware backend development.

---

## 🚀 Features

### 🛍 Product Management
- Add / Update / Delete Products
- Manage Categories
- Track Purchase Price & Selling Price
- Stock Quantity Management

### 💰 Sales Management
- Create Sales Records
- Automatic Total Amount Calculation
- Automatic Profit Calculation
- Track Sales Date-wise

### 📊 Reports & Analytics
- Last N Sales (Optimized Fetching)
- Category-wise Sales Summary
- Category-wise Profit Summary
- Quantity Sold per Category
- Old Stock Detection (Based on months)
- Monthly & Last 30 Days Reporting Logic

### ⚡ Performance Optimizations
- Limited record fetching (avoid large payloads)
- JPQL custom queries
- DTO-based projections
- Avoided unnecessary entity loading
- Stream API usage for data transformation
- Considered pagination for large datasets

---

## 🛠 Tech Stack

### 🔹 Backend
- Java 21
- Spring Boot 4.0.1
- Spring Data JPA
- Hibernate
- Lombok
- MySQL

### 🔹 Frontend
- Thymeleaf
- HTML5
- CSS3
- Bootstrap
- JavaScript

### 🔹 Version Control
- Git
- GitHub

---

## 🏗 Project Architecture

This project follows a clean layered architecture:

Controller Layer  
→ Handles HTTP requests and API endpoints

Service Layer  
→ Contains business logic and calculations

Repository Layer  
→ Database interaction using Spring Data JPA

Entity Layer  
→ Represents database tables

DTO Layer  
→ Used for optimized data transfer and custom projections

---

## 📂 Folder Structure
    src/main/java
    ├── controller
    ├── service
    ├── repository
    ├── entity
    ├── dto

    src/main/resources
    ├── templates (Thymeleaf UI)
    ├── static (CSS, JS)
    ├── application.properties


---

## 📊 Reporting APIs

### 1️⃣ Last N Sales
Fetch latest N sales records ordered by date.

Example:
GET /api/reports/last-n-sales?n=10

---

### 2️⃣ Category-wise Sales & Profit Summary
Returns:
- Category
- Total Sales
- Total Profit
- Total Quantity Sold

Uses optimized JPQL query for aggregation.

---

### 3️⃣ Old Stock Report
Fetch products that have not been sold for X months.

Example:
GET /api/reports/old-stock?months=6

---

## 🧠 Key Concepts Implemented

- Aggregation Queries using JPQL
- Group By operations
- SUM() calculations for profit & quantity
- Stream API for transformation
- DTO projection for optimized API responses
- Performance handling for large record sets
- Clean Git workflow in team collaboration

---

## ⚙️ How To Run

### 1️⃣ Clone the Repository
    git clone https://github.com/Akash527-alt/Clothing-Management-System.git


### 2️⃣ Configure Database

Update `application.properties`:

    spring.datasource.url=jdbc:mysql://localhost:3306/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password


### 3️⃣ Run the Application

Run the Spring Boot application.

Application will start at:
http://localhost:{portNumber}


---

## 📈 Future Improvements

- Add Pagination to reports
- Add Authentication & Authorization (Spring Security)
- Add Dashboard with Charts
- Deploy to Cloud (AWS / Render / Railway)
- Add Swagger API Documentation
- Add Unit & Integration Tests

---

## 👨‍💻 Authors

Akash Prajapati
Bachelor of Computer Science (Expected 2027)

GitHub:
https://github.com/Akash527-alt

Darshan Parekh
Bachelor of Computer Science (Expected 2027)

GitHub:
https://github.com/darshan2456

---

## 📄 License

This project is developed for educational and learning purposes.