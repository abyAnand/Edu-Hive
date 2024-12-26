
DROP TABLE IF EXISTS customer_course;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS user_entity;
DROP TABLE IF EXISTS creator;

-- Create the creator table
CREATE TABLE creator (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create the user_entity table
CREATE TABLE user_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create the course table
CREATE TABLE course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE NOT NULL,
    creator_id BIGINT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_course_creator FOREIGN KEY (creator_id) REFERENCES creator (id) ON DELETE CASCADE
);

-- Create the customer_course join table
CREATE TABLE customer_course (
    course_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    PRIMARY KEY (course_id, customer_id),
    CONSTRAINT fk_customer_course_course FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE,
    CONSTRAINT fk_customer_course_customer FOREIGN KEY (customer_id) REFERENCES user_entity (id) ON DELETE CASCADE
);
