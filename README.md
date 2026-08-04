# 🚀 REST Assured API Automation Framework

A scalable and reusable API Automation Framework built using **Java**, **Rest Assured**, **TestNG**, and **Maven** following industry best practices.

---

# 📌 Tech Stack

- Java 21
- Rest Assured
- TestNG
- Maven
- Jackson
- Log4j2
- Allure Reports
- DataFaker
- JSON Schema Validator
- Git & GitHub

---

# ✨ Features

- ✅ Restful Booker CRUD Automation
- ✅ FakeStore API CRUD Automation
- ✅ POJO Serialization & Deserialization
- ✅ Request & Response Specification
- ✅ Configurable Base URLs
- ✅ Token Management
- ✅ Data Builder Pattern
- ✅ TestNG DataProvider
- ✅ Reusable Assertion Helper
- ✅ JSON Schema Validation
- ✅ Log4j2 Logging
- ✅ Allure Reporting
- ✅ Git Feature Branch Workflow

---

# 📂 Project Structure

```
src
├── main
│   ├── java
│   │   ├── auth
│   │   ├── builders
│   │   ├── config
│   │   ├── pojo
│   │   ├── routes
│   │   ├── services
│   │   ├── specifications
│   │   └── utils
│
├── test
│   ├── java
│   │   ├── dataproviders
│   │   ├── listeners
│   │   ├── CreateBookingTest
│   │   ├── ProductTest
│   │   └── BaseTest
│   │
│   └── resources
│       ├── schemas
│       ├── config.properties
│       └── log4j2.xml
```

---

# 📋 APIs Covered

## Restful Booker

- Generate Token
- Create Booking
- Get Booking
- Update Booking
- Delete Booking

---

## FakeStore API

- Get All Products
- Get Product By Id
- Create Product
- Update Product
- Delete Product

---

# ✅ Assertions

- verifyEquals()
- verifyTrue()
- verifyFalse()
- verifyNotNull()
- verifyStatusCode()
- verifyProduct()
- verifyBooking()

---

# 📄 JSON Schema Validation

Framework validates API contract using JSON Schema.

Example:

```java
response.then()
        .body(matchesJsonSchemaInClasspath("schemas/product-schema.json"));
```

---

# 📊 Reporting

## Allure Report

Generate Report

```bash
allure serve allure-results
```

---

# 📝 Logging

Framework uses **Log4j2** for logging.

Example:

```java
logger.info("Creating Product");
```

---

# ▶️ Execute Tests

Run complete suite

```bash
mvn clean test
```

Run specific TestNG suite

```bash
mvn test
```

---

# 🌿 Git Workflow

```
main
   │
feature/*
   │
Commit
   │
Push
   │
Pull Request
   │
Merge
```

---

# 📈 Framework Highlights

- Reusable Design
- Clean Architecture
- Modular Services
- Builder Pattern
- POJO Mapping
- Centralized Configuration
- Data Driven Testing
- Contract Validation
- Scalable Framework

---

# 👨‍💻 Author

**Anurag Pandey**

QA Automation Engineer

Java | Rest Assured | Selenium | TestNG | Maven | API Automation
