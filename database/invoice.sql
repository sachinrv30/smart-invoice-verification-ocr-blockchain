-- SQL script placeholder
-- ===========================================
-- Smart Invoice Verification Database
-- ===========================================

DROP DATABASE IF EXISTS smart_invoice;
CREATE DATABASE smart_invoice;
USE smart_invoice;

-- ===========================================
-- Users Table
-- ===========================================

CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       full_name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       phone VARCHAR(15),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===========================================
-- Admin Table
-- ===========================================

CREATE TABLE admin (
                       admin_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

-- ===========================================
-- Invoice Table
-- ===========================================

CREATE TABLE invoice (
                         invoice_id INT AUTO_INCREMENT PRIMARY KEY,
                         invoice_number VARCHAR(100) NOT NULL,
                         vendor_name VARCHAR(100) NOT NULL,
                         invoice_date DATE,
                         gst_number VARCHAR(30),
                         customer_name VARCHAR(100),
                         product_name VARCHAR(200),
                         quantity INT,
                         price DECIMAL(12,2),
                         gst_amount DECIMAL(12,2),
                         total_amount DECIMAL(12,2),
                         invoice_hash VARCHAR(255),
                         file_path TEXT,
                         status VARCHAR(30) DEFAULT 'Pending',
                         uploaded_by INT,
                         uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                         FOREIGN KEY(uploaded_by)
                             REFERENCES users(user_id)
);

-- ===========================================
-- OCR Data
-- ===========================================

CREATE TABLE ocr_data (

                          ocr_id INT AUTO_INCREMENT PRIMARY KEY,

                          invoice_id INT,

                          extracted_text LONGTEXT,

                          extracted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                          FOREIGN KEY(invoice_id)
                              REFERENCES invoice(invoice_id)

);

-- ===========================================
-- Blockchain Table
-- ===========================================

CREATE TABLE blockchain (

                            block_id INT AUTO_INCREMENT PRIMARY KEY,

                            invoice_id INT,

                            previous_hash VARCHAR(255),

                            current_hash VARCHAR(255),

                            nonce BIGINT,

                            block_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                            FOREIGN KEY(invoice_id)
                                REFERENCES invoice(invoice_id)

);

-- ===========================================
-- Fraud Logs
-- ===========================================

CREATE TABLE fraud_logs (

                            fraud_id INT AUTO_INCREMENT PRIMARY KEY,

                            invoice_id INT,

                            fraud_type VARCHAR(100),

                            description TEXT,

                            detected_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                            FOREIGN KEY(invoice_id)
                                REFERENCES invoice(invoice_id)

);

-- ===========================================
-- Audit Logs
-- ===========================================

CREATE TABLE audit_logs (

                            log_id INT AUTO_INCREMENT PRIMARY KEY,

                            user_id INT,

                            action VARCHAR(255),

                            action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                            FOREIGN KEY(user_id)
                                REFERENCES users(user_id)

);

-- ===========================================
-- Default Admin
-- Username : admin
-- Password : admin123
-- (Will be hashed later in Java)
-- ===========================================

INSERT INTO admin(username,password)
VALUES('admin','admin123');