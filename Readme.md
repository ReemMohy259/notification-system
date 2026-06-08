# 📩 Notification System
![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

A simple **event-driven notification system** built using **Spring Boot, RabbitMQ, Docker, and a simple frontend UI**.
The system demonstrates asynchronous communication between microservices using message queues.

---

# 🧠 System Overview

This project simulates a real-world notification workflow:

> User registers → Producer saves user → publishes event → RabbitMQ → Consumer processes event → Frontend retrieves notification

---

## 🏗️ Architecture

```text
Frontend (HTML/CSS/JS)
        │
        ▼
Producer Service (Spring Boot)
        │
        ▼
RabbitMQ Exchange
        │
        ▼
Queue (notification.queue)
        │
        ▼
Consumer Service (Spring Boot)
        │
        ▼
In-Memory Storage + API
```

---

### 🔵 Backend — Java / Spring Boot

| Technology | Purpose |
|---|---|
| **Java 21** | Core programming language |
| **Spring Boot** | Application framework |
| **Spring Web (REST)** | REST API layer |
| **Spring Data JPA** | ORM & data access |
| **Spring AMQP** | RabbitMQ integration |
| **Hibernate** | JPA implementation / ORM |

### 🟠 Messaging — RabbitMQ

| Component | Details |
|---|---|
| **Exchange Type** | Direct Exchange |
| **Queue Name** | `notification.queue` |
| **Routing Key** | Configurable per event type |
| **Protocol** | AMQP 0-9-1 |

### 🟢 Data Layer

| Technology | Purpose |
|---|---|
| **MySQL 8** | Persistent storage for registered users |
| **In-Memory Storage** | Consumer-side notification caching |

### 🌐 Frontend

| Technology | Purpose |
|---|---|
| **HTML5** | Markup structure |
| **CSS3** | Styling and layout |
| **JavaScript** | Interactivity & Fetch API calls |

### 🐳 DevOps & Infrastructure

| Technology | Purpose |
|---|---|
| **Docker** | Service containerization |
| **Docker Compose** | Multi-container orchestration |
| **RabbitMQ Management UI** | Queue monitoring & debugging |
 
---

# 📦 Microservices

## 1. Producer Service (Registration Service)

* REST API: `POST /api/register`
* Saves user in MySQL
* Publishes event to RabbitMQ

---

## 2. Consumer Service (Email Service)

* Listens to RabbitMQ queue
* Stores received messages in memory
* Exposes:

    * `GET /notifications`
    * `GET /notifications/latest`

---

## 3. Frontend Service

* Simple UI:

    * Register user form
    * Receive notification button
* Communicates with:

    * Producer API
    * Consumer API

---

## 4. Infrastructure Services

* RabbitMQ (message broker)
* MySQL (database)

---

# 🔄 Message Flow

```text
User submits form
        ↓
Producer Service
        ↓
RabbitMQ Exchange
        ↓
notification.queue
        ↓
Consumer Service
        ↓
Stored in memory
        ↓
Frontend requests latest notification
```

---

# 🚀 How to Run the Project

## 1. Clone Repository

```bash
git clone <repo-url>
cd project
```

---

## 2. Run with Docker Compose

```bash
docker compose up --build -d
```

---

## 3. Access Services

| Service      | URL                                              |
| ------------ | ------------------------------------------------ |
| Frontend     | [http://localhost](http://localhost)             |
| Producer API | [http://localhost:8080](http://localhost:8080)   |
| Consumer API | [http://localhost:8081](http://localhost:8081)   |
| RabbitMQ UI  | [http://localhost:15672](http://localhost:15672) |

---

# 🧪 API Endpoints

## Producer

### Register User

```http
POST /api/register
```

```json
{
  "firstName": "Reem",
  "lastName": "Mohy",
  "email": "reem@test.com",
  "phoneNumber": "01000000000"
}
```

---

## Consumer

### Get Latest Notification

```http
GET /notifications/latest
```

### Get All Notifications

```http
GET /notifications
```

---

# 🗄️ Database Schema

### Users Table

```text
id (UUID)
first_name
last_name
email (unique)
phone_number
created_at
```

---

# 📸 Screenshots

## 🧾 1. Successful Registration

---

## 📩 2. Notification Received


---

## 🗄️ 3. MySQL Database Table

---

## 🐰 4. RabbitMQ Management Board

---

# 📂 Project Structure

```text
email-service/
registration-service/
frontend/
docker-compose.yml
```

---

# 🔥 Key Features

* Event-driven architecture using RabbitMQ
* Decoupled microservices
* REST APIs for communication
* In-memory caching in consumer
* Simple frontend integration
* Fully containerized system
* Real-time message processing

---

# 📈 Future Improvements

* Add Email SMTP service (real emails)
* Add Audit logging service
* Add Dead Letter Queue (DLQ)
* Add Redis caching for notifications
* Add React frontend
* Add API Gateway

---
Happy Messaging! 🚀
