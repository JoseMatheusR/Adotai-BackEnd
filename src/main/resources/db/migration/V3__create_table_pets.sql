CREATE TABLE IF NOT EXISTS pet (
    id                     VARCHAR(255) PRIMARY KEY,
    name                   VARCHAR(255) NOT NULL,
    type                   VARCHAR(255) NOT NULL,
    breed                   VARCHAR(255),
    age                    SMALLINT,
    observations           VARCHAR(255),
    status                 VARCHAR(255),
    created_at             TIMESTAMP,
    caring_organization_id VARCHAR(255) REFERENCES organization (id) NOT NULL
);