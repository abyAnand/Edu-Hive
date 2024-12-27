
DROP TABLE IF EXISTS customer_course;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS user_entity;
DROP TABLE IF EXISTS creator;

-- Create Creator table
CREATE TABLE creator (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Create User table (for UserEntity)
CREATE TABLE user_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL, -- Assuming Role is a string (can be enum value)
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);


-- Create Course table
CREATE TABLE course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price DOUBLE NOT NULL,
    creator_id BIGINT NOT NULL,  -- Foreign key linking to creator
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (creator_id) REFERENCES creator(id)  -- One-to-Many relationship
);

-- Create Customer table
CREATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);


-- Create many-to-many relationship table for customers and courses
CREATE TABLE customer_course (
    course_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    PRIMARY KEY (course_id, customer_id),
    FOREIGN KEY (course_id) REFERENCES course(id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);
