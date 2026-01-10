-- liquibase formatted sql

-- changeset gemini:20260110-01
-- comment: Create user demo
CREATE TABLE user_demo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
-- rollback DROP TABLE user_demo;

-- changeset gemini:20260110-02
-- comment: Insert
INSERT INTO user_demo (username, password, email) VALUES ('admin', '{noop}123456', 'admin@example.com');
-- rollback DELETE FROM user_demo WHERE username = 'admin';