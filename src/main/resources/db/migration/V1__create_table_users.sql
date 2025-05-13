CREATE TABLE IF NOT EXISTS Users (
    id             VARCHAR(255) PRIMARY KEY,
    name           VARCHAR(255),
    email          VARCHAR(255) UNIQUE,
    password       VARCHAR(255),
    birthdate      VARCHAR(20),
    phone          VARCHAR(20),
    email_verified BOOLEAN,
    created_at     TIMESTAMP
);