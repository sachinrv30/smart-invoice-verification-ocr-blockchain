<div align="center">

# 🚀 SmartLedger AI

### 🔐 Smart Invoice Verification System using OCR, AI & Blockchain-Inspired Security

**Turning invoice verification into an intelligent, secure and automated workflow.**

<p>
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk" alt="Java 17">
  <img src="https://img.shields.io/badge/Java%20Swing-GUI-blue?style=for-the-badge" alt="Java Swing">
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/OCR-Invoice%20Extraction-green?style=for-the-badge" alt="OCR">
  <img src="https://img.shields.io/badge/AI-Fraud%20Detection-purple?style=for-the-badge" alt="AI">
  <img src="https://img.shields.io/badge/SHA--256-Integrity%20Verification-red?style=for-the-badge" alt="SHA-256">
  <img src="https://img.shields.io/badge/Maven-Build-orange?style=for-the-badge&logo=apachemaven" alt="Maven">
</p>

<p>
  <a href="#-overview">Overview</a> •
  <a href="#-key-features">Features</a> •
  <a href="#-system-workflow">Workflow</a> •
  <a href="#-architecture">Architecture</a> •
  <a href="#-technology-stack">Technology</a> •
  <a href="#-screenshots">Screenshots</a> •
  <a href="#-installation">Installation</a> •
  <a href="#-future-scope">Future Scope</a>
</p>

</div>

---

# 🧠 Overview

**SmartLedger AI** is a Java-based desktop application designed to simplify and strengthen the invoice verification process.

The system combines **OCR-based invoice extraction, AI-assisted fraud analysis, invoice validation, blockchain-inspired hash verification, secure authentication, analytics and reporting** into a centralized workflow.

Instead of relying completely on manual invoice inspection, SmartLedger AI provides a structured process for:

```text
📄 Invoice
    ↓
🔎 OCR Extraction
    ↓
📋 Data Validation
    ↓
🤖 AI-Assisted Analysis
    ↓
🛡️ Fraud / Suspicious Detection
    ↓
⛓️ Hash-Based Integrity Verification
    ↓
📊 Dashboard & Analytics
    ↓
📑 Reports
````

> **Note:** The blockchain component is a local, blockchain-inspired hash-chain integrity mechanism. It is not a distributed public blockchain network.

---

# 🎯 The Problem

Traditional invoice verification can involve:

* Manual data entry
* Manual document inspection
* Duplicate invoice checking
* Authenticity verification
* Fraud investigation
* Record maintenance
* Repetitive administrative work

These processes can increase processing time and introduce opportunities for human error.

## 💡 The SmartLedger Approach

SmartLedger AI brings these activities together into a single desktop platform.

```text
                 ┌─────────────────────┐
                 │      📄 INVOICE      │
                 └──────────┬──────────┘
                            ↓
                 ┌─────────────────────┐
                 │    🔎 OCR ENGINE    │
                 └──────────┬──────────┘
                            ↓
                 ┌─────────────────────┐
                 │  📋 DATA VALIDATION │
                 └──────────┬──────────┘
                            ↓
                 ┌─────────────────────┐
                 │   🤖 AI ANALYSIS    │
                 └──────────┬──────────┘
                            ↓
                 ┌─────────────────────┐
                 │ 🛡️ FRAUD ANALYSIS   │
                 └──────────┬──────────┘
                            ↓
                 ┌─────────────────────┐
                 │ ⛓️ HASH VERIFICATION│
                 └──────────┬──────────┘
                            ↓
                 ┌─────────────────────┐
                 │   📊 DASHBOARD      │
                 └──────────┬──────────┘
                            ↓
                 ┌─────────────────────┐
                 │    📑 REPORTS       │
                 └─────────────────────┘
```

---

# ✨ Key Features

## 🔐 Secure Authentication

* Employee registration
* Employee login
* Password hashing
* Role-based access
* Employee/Admin separation
* Password visibility control
* Forgot-password workflow

---

## 📄 Invoice Management

Users can manage invoice records through a centralized interface.

### Capabilities

* Upload invoices
* View invoice records
* Search invoice information
* Manage invoice details
* Preview invoice information
* Validate invoice records
* Track invoice status

---

## 🔎 OCR Invoice Extraction

The OCR module extracts information from invoice documents and converts it into structured invoice data.

### Processing Flow

```text
Invoice Document
       ↓
   OCR Engine
       ↓
 Text Extraction
       ↓
 Invoice Parsing
       ↓
Structured Data
```

Typical invoice information may include:

| Field          | Example          |
| -------------- | ---------------- |
| Invoice Number | INV-2026-001     |
| Vendor         | ABC Technologies |
| Date           | 2026-09-20       |
| Amount         | ₹25,000          |

---

# 🤖 AI-Assisted Fraud Detection

SmartLedger AI analyzes invoice information to help identify potentially suspicious records.

The analysis can consider:

* Duplicate invoice information
* Similar invoice records
* Suspicious invoice values
* Existing invoice records
* Validation results
* Invoice-related patterns

The application provides clear verification states such as:

```text
🟢 VERIFIED

🔴 FRAUD / SUSPICIOUS
```

> The fraud-detection component is an application-level analysis mechanism and can be extended with advanced machine-learning models in future versions.

---

# ⛓️ Blockchain-Inspired Integrity Verification

SmartLedger AI uses a **hash-chain mechanism inspired by blockchain principles** to help protect invoice record integrity.

Each block maintains a relationship with the previous block.

```text
┌─────────────────────────┐
│        BLOCK 1          │
│                         │
│ Invoice ID              │
│ Current Hash            │
│ Previous Hash = 0       │
└────────────┬────────────┘
             ↓
┌─────────────────────────┐
│        BLOCK 2          │
│                         │
│ Invoice ID              │
│ Previous Hash = Hash 1  │
│ Current Hash            │
└────────────┬────────────┘
             ↓
┌─────────────────────────┐
│        BLOCK 3          │
│                         │
│ Invoice ID              │
│ Previous Hash = Hash 2  │
│ Current Hash            │
└─────────────────────────┘
```

If the relationship between blocks does not match, the application can identify a potential integrity issue.

---

# 🔑 SHA-256 Hash Verification

Conceptually, the system creates a cryptographic representation of invoice/block information:

```text
Invoice Data
     +
Previous Hash
     +
Timestamp
     ↓
   SHA-256
     ↓
 Current Hash
```

A modification to the underlying information produces a different hash, allowing the system to detect potential changes to stored records.

---

# 📊 Analytics Dashboard

The dashboard provides a centralized view of invoice activity.

### Dashboard Information

* Total invoices
* Verified invoices
* Fraud/suspicious invoices
* Blockchain blocks
* Recent invoice records
* Invoice status
* Vendor information
* Invoice amounts
* Invoice dates
* Quick actions

---

# 📑 Reports

The reporting module provides invoice-related reporting capabilities.

Supported reporting workflows include:

* 📄 PDF
* 📊 Excel
* 📋 CSV

---

# 👁️ Invoice Preview

The application includes an invoice preview interface that allows users to inspect invoice information before or after verification.

---

# 🗄️ Database

SmartLedger AI uses **MySQL** for persistent data storage.

### Database Structure

```text
smart_invoice
│
├── users
├── invoice
├── ocr_results
└── blockchain
```

### Users

Stores employee authentication and profile information.

```text
user_id
full_name
email
password
phone
role
created_at
```

### Invoice

Stores invoice-related information.

```text
invoice_id
invoice_number
vendor_name
invoice_date
amount
file_name
file_path
status
created_at
```

### OCR Results

Stores information extracted from invoice documents.

### Blockchain

Stores hash-chain records used for integrity verification.

---

# 🏗️ Architecture

SmartLedger AI follows a modular layered architecture.

```text
┌─────────────────────────────────┐
│         USER INTERFACE          │
│            Java Swing           │
└────────────────┬────────────────┘
                 ↓
┌─────────────────────────────────┐
│          SERVICE LAYER          │
│                                 │
│ Authentication                 │
│ OCR                            │
│ Validation                     │
│ Fraud Detection                │
│ Blockchain Verification        │
│ Reporting                      │
│ Analytics                      │
└────────────────┬────────────────┘
                 ↓
┌─────────────────────────────────┐
│            DAO LAYER            │
│          JDBC / DAO             │
└────────────────┬────────────────┘
                 ↓
┌─────────────────────────────────┐
│             MySQL               │
│            Database             │
└─────────────────────────────────┘
```

---

# 🧩 Technology Stack

| Technology               | Purpose                           |
| ------------------------ | --------------------------------- |
| **Java 17**              | Core application development      |
| **Java Swing**           | Desktop user interface            |
| **MySQL**                | Persistent database               |
| **JDBC**                 | Database connectivity             |
| **Apache Maven**         | Dependency and build management   |
| **OCR**                  | Invoice text extraction           |
| **SHA-256**              | Cryptographic hashing             |
| **AI-assisted analysis** | Fraud/suspicious invoice analysis |
| **Git & GitHub**         | Version control                   |

---

# 📁 Project Structure

```text
SmartInvoiceVerification
│
├── database
│
├── docs
│   └── screenshots
│       ├── about-system.png
│       ├── admin-login.png
│       ├── analytics.png
│       ├── blockchain.png
│       ├── dashboard.png
│       ├── fraud-detection.png
│       ├── invoice-management.png
│       ├── login.png
│       ├── ocr-result.png
│       ├── profile.png
│       ├── register.png
│       ├── reports.png
│       └── upload-invoice.png
│
├── src
│   └── main
│       ├── java
│       │   └── com.smartinvoice
│       │       ├── auth
│       │       ├── blockchain
│       │       ├── charts
│       │       ├── config
│       │       ├── dao
│       │       ├── fraud
│       │       ├── model
│       │       ├── ocr
│       │       ├── parser
│       │       ├── report
│       │       ├── service
│       │       ├── ui
│       │       ├── utils
│       │       └── validation
│       │
│       └── resources
│           ├── backgrounds
│           ├── css
│           ├── icons
│           ├── images
│           └── invoices
│
├── uploads
├── pom.xml
├── .gitignore
└── README.md
```

---

# 🔄 System Workflow

```text
                    START
                      │
                      ▼
              🔐 Employee Login
                      │
                      ▼
               📄 Upload Invoice
                      │
                      ▼
                  🔎 OCR
                      │
                      ▼
              📋 Extract Data
                      │
                      ▼
                ✅ Validation
                      │
                      ▼
                🤖 AI Analysis
                      │
              ┌───────┴────────┐
              ▼                ▼
        🟢 VERIFIED      🔴 SUSPICIOUS
              │                │
              └───────┬────────┘
                      ▼
              ⛓️ Hash Verification
                      │
                      ▼
                📊 Dashboard
                      │
                      ▼
                  📑 Reports
                      │
                      ▼
                     END
```

---

# 🚀 Installation

## Prerequisites

Install:

* Java JDK 17+
* IntelliJ IDEA
* MySQL Server
* Apache Maven

---

## 1. Clone the Repository

```bash
git clone https://github.com/sachinrv30/smart-invoice-verification-ocr-blockchain.git
```

Move into the project:

```bash
cd smart-invoice-verification-ocr-blockchain
```

---

## 2. Open in IntelliJ IDEA

Open the cloned project in IntelliJ IDEA.

Allow Maven to download and configure the required dependencies.

---

## 3. Create the Database

Open MySQL and create:

```sql
CREATE DATABASE smart_invoice;
```

Then execute the SQL schema provided in the project's `database` directory.

---

## 4. Configure Database Credentials

The database password should not be stored directly in the source code.

Set the environment variable:

```text
SMARTLEDGER_DB_PASSWORD=YOUR_PASSWORD
```

In IntelliJ IDEA:

```text
Run
 ↓
Edit Configurations
 ↓
Environment Variables
 ↓
SMARTLEDGER_DB_PASSWORD=YOUR_PASSWORD
```

---

## 5. Run the Application

Run the project's main application class from IntelliJ IDEA.

The SmartLedger AI desktop interface should launch.

---

# 🔐 Security

Sensitive configuration values are intentionally excluded from the repository.

The database connection uses:

```text
Environment Variable
       ↓
Database Credential
       ↓
JDBC Connection
       ↓
MySQL
```

Security-related mechanisms include:

* Password hashing
* Environment-based database credentials
* SHA-256 hashing
* Role-based authentication
* Hash-chain integrity verification
* `.gitignore` protection for sensitive configuration

---

# 📸 Screenshots

## 🔐 Employee Login

<p align="center">
  <img src="docs/screenshots/login.png" alt="SmartLedger AI Employee Login" width="900">
</p>

---

## 📝 Employee Registration

<p align="center">
  <img src="docs/screenshots/register.png" alt="SmartLedger AI Registration" width="900">
</p>

---

## 🏠 SmartLedger AI Dashboard

<p align="center">
  <img src="docs/screenshots/dashboard.png" alt="SmartLedger AI Dashboard" width="900">
</p>

---

## 📤 Upload Invoice

<p align="center">
  <img src="docs/screenshots/upload-invoice.png" alt="SmartLedger AI Upload Invoice" width="900">
</p>

---

## 🔎 OCR Invoice Extraction

<p align="center">
  <img src="docs/screenshots/ocr-result.png" alt="SmartLedger AI OCR Result" width="900">
</p>

---

## 🧾 Invoice Management

<p align="center">
  <img src="docs/screenshots/invoice-management.png" alt="SmartLedger AI Invoice Management" width="900">
</p>

---

## ⛓️ Blockchain Verification

<p align="center">
  <img src="docs/screenshots/blockchain.png" alt="SmartLedger AI Blockchain Verification" width="900">
</p>

---

## 🛡️ AI Fraud Detection

<p align="center">
  <img src="docs/screenshots/fraud-detection.png" alt="SmartLedger AI Fraud Detection" width="900">
</p>

---

## 📊 Analytics Dashboard

<p align="center">
  <img src="docs/screenshots/analytics.png" alt="SmartLedger AI Analytics" width="900">
</p>

---

## 📑 Reports

<p align="center">
  <img src="docs/screenshots/reports.png" alt="SmartLedger AI Reports" width="900">
</p>

---

## 👤 User Profile

<p align="center">
  <img src="docs/screenshots/profile.png" alt="SmartLedger AI User Profile" width="900">
</p>

---

## 🔑 Administrator Login

<p align="center">
  <img src="docs/screenshots/admin-login.png" alt="SmartLedger AI Administrator Login" width="900">
</p>

> The current version provides an Administrator Login interface. A complete administrator management portal is planned as future development.

---

## ℹ️ About System

<p align="center">
  <img src="docs/screenshots/about-system.png" alt="About SmartLedger AI" width="900">
</p>

---

# 👨‍💼 Administrator Portal — Future Enhancement

The current application includes an Administrator Login interface.

A complete administrator management portal can be extended with:

```text
                    ADMINISTRATOR
                          │
          ┌───────────────┼───────────────┐
          ↓               ↓               ↓
     👥 Employees      📄 Invoices     📊 Analytics
          │               │               │
          └───────────────┼───────────────┘
                          ↓
                  🛡️ Fraud Monitoring
                          │
                          ↓
                     📑 Reports
                          │
                          ↓
                  ⛓️ Blockchain Audit
```

---

# 🌱 Future Scope

SmartLedger AI can be extended with additional enterprise capabilities.

### ☁️ Cloud Deployment

Deploy the platform on cloud infrastructure for centralized remote access.

### 🤖 Advanced Machine Learning

Train advanced machine-learning models using historical invoice data for improved fraud analysis.

### 📱 Mobile Application

Develop Android and iOS applications for mobile invoice verification.

### 📧 Automated Notifications

Send email or SMS notifications when invoices are verified or flagged.

### 🔗 QR Code Verification

Generate QR codes for invoices and enable instant authenticity verification.

### ✍️ Digital Signatures

Integrate digital signature verification for stronger document authenticity.

### 🏢 Multi-Organization Support

Support multiple companies, departments and organizational roles.

### 🔌 ERP Integration

Provide REST APIs for integration with accounting, ERP and enterprise systems.

### 👨‍💼 Advanced Administrator Portal

Add complete user management, role management, audit logs, monitoring and administrative controls.

---

# 🎓 Academic Project

**Project Name:** SmartLedger AI

**Project Title:** Smart Invoice Verification System using OCR, AI and Blockchain-Inspired Hash Verification

**Program:** MCA

---

# ⭐ Project Highlights

```text
🔎 OCR
   ↓
Extract invoice information

🤖 AI
   ↓
Analyze potentially suspicious invoices

⛓️ Hash Verification
   ↓
Verify invoice record integrity

🔐 Security
   ↓
Protect application access

📊 Analytics
   ↓
Understand invoice activity

📑 Reports
   ↓
Generate verification records
```

---

# 📌 Project Status

### 🟢 Core System Implemented

The current system includes:

* Authentication
* Invoice management
* OCR processing
* Invoice validation
* Fraud/suspicious invoice analysis
* Hash-based integrity verification
* Analytics
* Reporting
* Invoice preview
* Employee interface
* Administrator login interface

### 🔵 Future Development

The administrator management portal and additional enterprise-level capabilities can be developed in future versions.

---

<div align="center">

# ✨ Built to Make Invoice Verification Smarter

### SmartLedger AI

**OCR • AI-Assisted Analysis • Secure Authentication • Hash-Based Integrity • Analytics • Reporting**

<br>

## 👨‍💻 Designed & Developed By

# **SACHIN R V**

### MCA Student • Software Developer • AI & Application Development

<br>

<a href="https://github.com/sachinrv30">
  <img src="https://img.shields.io/badge/GitHub-sachinrv30-181717?style=for-the-badge&logo=github" alt="GitHub">
</a>

<br><br>

**© 2026 Sachin R V • SmartLedger AI**

</div>
