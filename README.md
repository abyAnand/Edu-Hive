# Digital Course Marketplace Backend

A simplified backend application for a Digital Course Marketplace supporting three user roles: **Admin**, **Creator**, and **Customer**. This application allows course creation, purchase, user management, and statistics tracking.

---

## Features

### Common APIs:
- **`POST /signup`**: User registration with roles (Admin, Creator, Customer).
- **`POST /login`**: User authentication with JWT-based security.

### Creator APIs:
- **`POST /course`**: Create a new course.
- **`GET /course`**: Retrieve all courses created by the logged-in creator.

### Customer APIs:
- **`GET /course`**: Fetch all available courses with optional search filters.
- **`GET /buy/course/:id`**: Purchase a course and add it to the customer's library.

### Admin APIs:
- **`GET /user`**: View all users (Creators and Customers).

### Statistics API:
- **`GET /stats`**: Retrieve bought courses and total revenue within a date range.

---

## Technology Stack

- **Programming Language**: Kotlin (JDK 21)
- **Framework**: Spring Boot 3
- **Build Tool**: Maven
- **Database**: H2 (In-memory database)
- **Authentication**: JWT
- **Password Security**: BCrypt
- **Containerization**: Docker

---

## Project Setup

### Prerequisites
1. Install JDK 21.
2. Install Maven.
3. Install Docker.

[//]: # (### Environment Variables)

[//]: # (Create a `.env` file in the root directory with the following variables:)

[//]: # ()
[//]: # (```env)

[//]: # (JWT_SECRET=your_jwt_secret_key)

[//]: # (JWT_EXPIRATION_MS=86400000)

[//]: # (DB_USERNAME=sa)

[//]: # (DB_PASSWORD=password)

[//]: # (```)

---

## Build and Run Locally

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/digital-course-marketplace.git
   cd digital-course-marketplace
   ```

2. **Build the Application**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application at `http://localhost:8080`.

---

## Run with Docker

1. **Build the Docker Image**:
   ```bash
   docker build -t digital-course-marketplace .
   ```

2. **Run the Docker Container**:
   ```bash
   docker run -p 8080:8080  digital-course-marketplace
   ```

3. Access the application at `http://localhost:8080`.

---

## Pull Image from Docker and run with Docker

1. **Pull the Docker Image**:
   ```bash
   docker pull abyanand/edu-hive:latest
   ```

2. **Run the Docker Container**:
   ```bash
   docker run -p 8080:8080  abyanand/edu-hive:latest .
   ```

3. Access the application at `http://localhost:8080`.

---