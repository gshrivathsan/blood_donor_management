
# Blood Donation Management System - Advanced Version

## Tech Stack
- Java 17
- Spring Boot 3
- Maven
- MySQL
- Vue.js + Vite
- Swagger/OpenAPI

---

# Features Included

## Authentication Module
- Register API
- Login API

## Donor Management
- Add donor
- Get all donors
- Update donor
- Delete donor

## Blood Request Module
- Create blood request
- Approve request
- Track status

## Hospital Module
- Add hospital
- Get hospitals

## Dashboard Module
- Total donors
- Total hospitals
- Total blood requests

---

# Setup Instructions

## 1. Install Requirements
- Java 17
- Maven
- MySQL
- Node.js

---

# 2. Create Database

Open MySQL and run:

```sql
CREATE DATABASE bloodbank;
```

---

# 3. Configure Database

Open:

backend/src/main/resources/application.properties

Update:

```properties
spring.datasource.username=root
spring.datasource.password=yourpassword
```

---

# 4. Run Backend

```bash
cd backend
mvn spring-boot:run
```

Backend runs at:

http://localhost:8080

Swagger:

http://localhost:8080/swagger-ui.html

---

# 5. Run Frontend

Open another terminal:

```bash
cd frontend
npm install
npm run dev
```

Frontend:

http://localhost:5173

---

# Included APIs

## Auth
POST /api/auth/login
POST /api/auth/register

## Donors
GET /api/donors
POST /api/donors
GET /api/donors/{id}
PUT /api/donors/{id}
DELETE /api/donors/{id}

## Hospitals
GET /api/hospitals
POST /api/hospitals

## Blood Requests
GET /api/requests
POST /api/requests
PUT /api/requests/{id}/approve

## Dashboard
GET /api/dashboard/stats

