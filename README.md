# Password Generator & Manager REST API

A REST API for generating, saving, and managing passwords, with user registration and login.  
Built with **Spring Boot**, **Spring Data JPA**, and **Email Notifications**.

---

## 🚀 Features
- User registration & login  
- Password generation with customizable length and character types  
- Save, update, search, and delete passwords  
- Email greeting on user registration  
- Basic error handling with meaningful HTTP status codes  

---

## 🛠️ Tech Stack
- Java 17  
- Spring Boot  
- Spring Web, Spring Data JPA  
- Jakarta Mail (email service)  
- Maven  
- PostgreSQL  

---

## ⚙️ Getting Started

### Prerequisites
- Java 17+  
- Maven  
- MySQL (or H2 for testing)  

### Installation
```bash
git clone <repo-url>
cd passwordGenerator
mvn spring-boot:run
