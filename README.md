# E-Commerce Backend API (Spring Boot)

Production-grade REST API for an e-commerce platform with JWT authentication and role-based access control (Admin/User).

## Features
- User registration and login (JWT-based)
- Role-based access (Admin/User)
- Product and category management
- Secure endpoints with Spring Security
- Global exception handling
- RESTful API design

## Tech Stack
- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- MySQL
- JWT

## Getting Started

### Prerequisites
- Java 17+
- Maven
- MySQL

### Setup
1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd sb-ecom
   ```
2. Configure your MySQL database in `src/main/resources/application.properties`.
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Usage
- Register a user: `POST /auth/signup`
- Login: `POST /auth/login` (returns JWT)
- Use JWT as `Authorization: Bearer <token>` in headers for protected endpoints
- Only Admins can create/update/delete products and categories
- Users can view products and categories

## Roles
- `ADMIN`: Full access to all endpoints
- `USER`: Read-only access to products and categories

## Example Admin User Creation
Register with `{ "user_role": "ADMIN" }` in the signup payload.

## Testing
- Use Postman or any REST client to test endpoints

## License
MIT 