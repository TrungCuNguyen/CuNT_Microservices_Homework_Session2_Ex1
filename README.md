# Order Service (E-Commerce Microservice)

Dự án microservice **Order-Service** được xây dựng chuẩn theo mô hình phân tầng kiến trúc (Layered Architecture) cho hệ sinh thái E-commerce.

---

## 🛠 Công Nghệ Sử Dụng

- **Framework**: Spring Boot 4.0.8
- **Java**: JDK 17 (Java Toolchain 17)
- **Build Tool**: Gradle 9.7 (với Gradle Wrapper)
- **Database**: PostgreSQL (Driver: `org.postgresql:postgresql`)
- **Cấu hình**: `application.properties`
- **Thư viện bổ trợ**:
  - Spring Data JPA / Hibernate
  - Spring Boot Starter WebMVC
  - Lombok
  - JUnit 5 & Mockito (Unit/Integration Test)
  - H2 Database (Test Profile)

---

## 📁 Cấu Trúc Dự Án (Layered Architecture)

```text
src/main/java/com/ecommerce/orderservice/
├── OrderServiceApplication.java      # Main application class
├── controller/
│   └── OrderController.java          # REST API endpoints (tách riêng tầng giao tiếp HTTP)
├── service/
│   ├── OrderService.java             # Interface định nghĩa business logic
│   └── impl/
│       └── OrderServiceImpl.java     # Cài đặt nghiệp vụ, xử lý dữ liệu và mapping DTO
├── repository/
│   └── OrderRepository.java          # Tầng truy xuất dữ liệu kế thừa JpaRepository
├── entity/
│   ├── Order.java                    # Entity Order ánh xạ bảng 'orders' trong PostgreSQL
│   └── OrderStatus.java              # Enum trạng thái đơn hàng (PENDING, PROCESSING,...)
├── dto/
│   ├── OrderRequestDto.java          # DTO nhận dữ liệu đầu vào khi tạo/cập nhật
│   └── OrderResponseDto.java         # DTO trả về cho client
└── exception/
    ├── ResourceNotFoundException.java# Custom Exception khi không tìm thấy đơn hàng
    ├── ErrorResponse.java            # Cấu trúc lỗi chuẩn JSON
    └── GlobalExceptionHandler.java   # @RestControllerAdvice xử lý tập trung ngoại lệ
```

---

## 🚀 API Endpoints

### 1. Health Check (Theo yêu cầu)
- **Method**: `GET`
- **URL**: `/api/v1/orders/health-check`
- **Response** (200 OK):
  ```text
  Order Service is Up
  ```

### 2. Danh sách đơn hàng
- **Method**: `GET`
- **URL**: `/api/v1/orders`
- **Response** (200 OK): Danh sách `OrderResponseDto`

### 3. Chi tiết đơn hàng theo ID
- **Method**: `GET`
- **URL**: `/api/v1/orders/{id}`
- **Response** (200 OK / 404 NOT FOUND): `OrderResponseDto` hoặc `ErrorResponse`

### 4. Tạo đơn hàng mới
- **Method**: `POST`
- **URL**: `/api/v1/orders`
- **Body**:
  ```json
  {
    "customerName": "Nguyen Van A",
    "customerEmail": "vana@example.com",
    "totalAmount": 250000.00,
    "status": "PENDING"
  }
  ```
- **Response** (201 CREATED): `OrderResponseDto`

---

## ⚙️ Cấu Hình Database (`application.properties`)

File cấu hình nằm tại: `src/main/resources/application.properties`

```properties
spring.application.name=order-service
server.port=8080

# PostgreSQL Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/order_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.open-in-view=false
```

---

## 🧪 Hướng Dẫn Chạy & Test

### Chạy Unit & Integration Tests:
```powershell
.\gradlew test
```

### Chạy ứng dụng:
```powershell
.\gradlew bootRun
```
hoặc đóng gói JAR:
```powershell
.\gradlew build
java -jar build/libs/order-service-0.0.1-SNAPSHOT.jar
```
