# 🎯 Spring Boot Mini Project: Calculator & Lottery API

This project is a lightweight Spring Boot application exposing two RESTful modules:

- **Calculator** – basic arithmetic operations with `undo` / `redo`.
- **Lottery** – prize-wheel drawing with concurrency safety, duplicate-draw guard, and real-time stats.

---

## 📦 Project Structure

```
src/main/java/
└── amway/olson/amway/test/
    ├── Application.java
    ├── calculator/
    │   ├── api/
    │   │   ├── CalculatorController.java
    │   │   ├── GlobalExceptionHandler.java
    │   │   └── OperationRequest.java
    │   ├── model/
    │   │   ├── Command.java
    │   │   ├── OperationCommand.java
    │   │   ├── OperationType.java
    │   │   └── ResultDto.java
    │   └── service/
    │       └── CalculatorService.java
    └── lottery/
        ├── api/
        │   └── LotteryController.java
        ├── model/
        │   ├── DrawRequest.java
        │   ├── DrawResponse.java
        │   ├── MultiDrawResponse.java
        │   ├── Prize.java
        │   └── StatsResponse.java
        └── service/
            └── LotteryService.java
```

---

## 🔢 Calculator API

| Method | Endpoint                 | Description                 |
|--------|--------------------------|-----------------------------|
| POST   | `/calculator/calculate`  | Perform an arithmetic op.   |
| POST   | `/calculator/undo`       | Roll back previous result.  |
| POST   | `/calculator/redo`       | Re-apply last undone op.    |

### 1. Calculate

```http
POST /calculator/calculate
```

```json
{
  "left": 1000,
  "right": 200,
  "operation": "SUBTRACT"
}
```

Success:

```json
{
  "result": 800.0
}
```

Invalid operation:

```json
{
  "error": "illegal operation type"
}
```

### 2. Undo

```http
POST /calculator/undo
```

```json
{
  "result": 1000.0
}
```

### 3. Redo

```http
POST /calculator/redo
```

```json
{
  "result": 800.0
}
```

---

## 🎰 Lottery API

| Method | Endpoint            | Description                                   |
|--------|---------------------|-----------------------------------------------|
| POST   | `/lottery/draw`     | Draw one or multiple times.                   |
| GET    | `/lottery/stats`    | Current inventory & participant list.         |

### 1. Draw

```http
POST /lottery/draw
```

```json
{
  "userId": "user",
  "count" : 5
}
```

Possible responses:

*Successful draw(s)*

```json
{
  "userId": "user",
  "results": ["iPhone", "THANK_YOU", "Coupon", "THANK_YOU", "iPad"]
}
```

*No stock or miss*

```json
{
  "userId": "user",
  "results": ["THANK_YOU"]
}
```

*Duplicate attempt*

```json
{
  "userId": "user",
  "results": ["DUPLICATE_DRAW"]
}
```

### 2. Stats

```http
GET /lottery/stats
```

```json
{
  "remaining": {
    "iPhone": 9,
    "iPad"  : 20,
    "Coupon": 28
  },
  "participants": ["user", "user1"]
}
```

---

## ⚙️ Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

By default, the application starts at **`http://localhost:8080`**.

---

## 🧪 Testing Notes

* **Calculator** uses the Command pattern to maintain an operation stack for reliable `undo` / `redo`.
* **Lottery** tracks each `userId` to block duplicate draws and relies on `AtomicInteger` counters for concurrency safety.
* All exceptions are centralized in `GlobalExceptionHandler` for consistent JSON error payloads.