# 🚀 Smart Complaint Management System

A full-stack Complaint Management System developed using **Spring Boot** following the **Layered Architecture**. The application allows users to register, log in securely, submit complaints, and track their complaint status. Administrators can view and manage all complaints through an admin dashboard.

---

## 📌 Features

### User Module
- User Registration
- User Login
- Submit Complaint
- View My Complaints
- Dashboard

### Admin Module
- View All Complaints
- Update Complaint Status
- Manage Users
- Dashboard with Complaint Statistics

---

## 🛠️ Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- Maven

### Frontend
- HTML5
- CSS3
- JavaScript

### Tools
- IntelliJ IDEA
- Postman
- Git
- GitHub

---

# 🏗️ Project Architecture

The project follows the Layered Architecture.

```
org.example.smartcomplaint
│
├── Config
│   ├── SecurityConfig
│   └── CustomUserDetailsService
│
├── Controller
│   ├── UserController
│   └── ComplaintController
│
├── DTO
│   ├── UserDTO
│   ├── LoginDTO
│   ├── ComplaintDTO
│   └── ApiResponse
│
├── Entity
│   ├── User
│   └── Complaint
│
├── Exception
│   ├── GlobalExceptionHandler
│   ├── ResourceNotFoundException
│   └── UserAlreadyExistsException
│
├── Repository
│   ├── UserRepository
│   └── ComplaintRepository
│
├── Service
│   ├── UserService
│   └── ComplaintService
│
└── SmartComplaintApplication
```

---

## 📂 Frontend Structure

```
resources
│
├── static
│   ├── css
│   │   ├── style.css
│   │   ├── dashboard.css
│   │   └── admin.css
│   │
│   ├── js
│   │   ├── login.js
│   │   ├── register.js
│   │   ├── complaint.js
│   │   ├── dashboard.js
│   │   ├── mycomplaints.js
│   │   └── admin.js
│   │
│   ├── index.html
│   ├── register.html
│   ├── dashboard.html
│   ├── complaint.html
│   ├── mycomplaints.html
│   └── admin.html
│
└── application.properties
```

---

## 🔐 Security Features

- Spring Security Authentication
- Password Encryption
- Role-Based Authorization
- User Authentication
- Secure REST APIs

---

## ⚙️ Exception Handling

- Global Exception Handling
- Resource Not Found Exception
- User Already Exists Exception
- Proper API Response Messages

---

## 📦 REST APIs

### User APIs

- POST /api/users/register
- POST /api/users/login

### Complaint APIs

- POST /api/complaints
- GET /api/complaints
- GET /api/complaints/user/{id}
- PUT /api/complaints/{id}
- DELETE /api/complaints/{id}

---

## ▶️ How to Run

1. Clone the repository

```bash
git clone https://github.com/yourusername/SmartComplaintSystem.git
```

2. Open in IntelliJ IDEA.

3. Configure MySQL database in `application.properties`.

4. Run the Spring Boot application.

5. Open your browser:

```
http://localhost:8081/index.html
```

---


## 👩‍💻 Author

**Punitha Shalini J**

Computer Science Engineering Student

Java | Spring Boot | MySQL | HTML | CSS | JavaScript

---

## ⭐ Future Enhancements

- Email Notifications
- Complaint Priority Management
- JWT Authentication
- File Upload Support
- Analytics Dashboard
- Responsive UI

---

## 📄 License

This project is developed for educational purposes.
