CREATE DATABASE bank_db;

USE bank_db;

CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    account_number VARCHAR(20) UNIQUE,
    account_type VARCHAR(50),
    balance DOUBLE,
    contact VARCHAR(100)
);

SELECT * FROM customers;

INSERT INTO customers (name, account_number, account_type, balance, contact)
VALUES ('Swapnil Doddi', '12345102', 'Savings', 6000, 8962572622),
('Akshay Patil', '12345103', 'Saving', 7000, 8975325827),
('Sneha Pawar', '12345104', 'Current', 12000, 4675325827);























