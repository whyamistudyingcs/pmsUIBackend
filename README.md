# PMS UI Backend Service

Backend service for the Portfolio Management System UI, providing REST APIs for authentication, account management, trade querying, and real-time updates via WebSocket.

## Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── fdm
│   │   │           └── pmsuibackend
│   │   │               ├── config/      # Configuration classes
│   │   │               ├── controller/  # REST controllers
│   │   │               ├── details/     # Security user details
│   │   │               ├── mapper/      # DTO-Entity mappers
│   │   │               └── service/     # Business logic
│   │   │                   └── security # Security services
```

## API Endpoints

### Authentication (`/api/auth`)
- `POST /register` - Register new user
  - Request: `UserCreationRequestDto`
  - Response: `UserRegistrationResponse`
- `POST /login` - Authenticate user
  - Request: `UserLoginDto`
  - Response: JWT token

### Account Management (`/api/account`)
- `POST /` - Create new account
  - Request: `AccountCreationDto`
  - Response: `AccountDto`
- `GET /{accountId}` - Get account by ID
  - Response: `AccountDto`
- `GET /` - Get all accounts for current user
  - Response: `List<AccountDto>`

### Trade Queries (`/api/trade-queries`)
- `GET /` - Get trade history
  - Request: `TradeHistoryRequestDto`
  - Response: `TradeHistoryResponseDto`
- `GET /id/{tradeId}` - Get trade by ID
  - Response: `TradeDto`
- `GET /external-id/{externalTradeId}` - Get trade by external ID
  - Response: `TradeDto`

### WebSocket Endpoints (`/topic`)
- `/sendMessage` - Send real-time messages
- `/topic/messages` - Subscribe to receive messages

## Security
- JWT-based authentication
- Validated request bodies using `@Valid`
- Access user details with Authentication.getPrincipal
- Related files: config/JWTFiler.java, config/SecurityConfig.java, service/security/*.java, details/userPrincipal.java 

## Error Handling
- **IMPORTANT**: For the sake of consistency and maintainability, all exception handling are stored in config/GlobalExceptionHandlerConfig. This handler should pick up all exceptions system thrown and return an error response to client
- Custom HTTP status codes for different scenarios
- Detailed error messages in responses
- Exception handling for authentication failures

## Building and Running
Setup .env on either project repository or root repository (repo that is storing all projects)

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The service will start on `http://localhost:8083`

## Dependencies
- Spring Boot 3.4.2
- Spring Security
- Spring WebSocket
- JWT Authentication
- pms-common library