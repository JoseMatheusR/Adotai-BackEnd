CREATE TABLE IF NOT EXISTS Organization (
    id             VARCHAR(255) PRIMARY KEY,
    document       VARCHAR(255) UNIQUE, -- CNPJ / Owner CPF
    name           VARCHAR(255),
    email          VARCHAR(255) UNIQUE,
    password       VARCHAR(255),
    contact_phone  VARCHAR(20),
    email_verified BOOLEAN,
    created_at     TIMESTAMP
);