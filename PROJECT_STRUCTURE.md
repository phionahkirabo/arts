# ARTS - Audit Recommendation Tracking System

## Project Structure

```
src/main/java/com/rra/arts/arts_backend/
├── config/              # Configuration classes (Security, CORS, etc.)
├── controller/          # REST API Controllers
├── dto/                 # Data Transfer Objects
│   └── ApiResponse.java # Standard API response wrapper
├── exception/           # Custom exceptions and global handler
│   ├── BadRequestException.java
│   ├── DatabaseException.java
│   ├── DuplicateResourceException.java
│   ├── FileStorageException.java
│   ├── ForbiddenException.java
│   ├── GlobalExceptionHandler.java
│   ├── InvalidRequestException.java
│   ├── ResourceNotFoundException.java
│   ├── UnauthorizedException.java
│   ├── ValidationException.java
│   └── WorkflowException.java
├── model/               # JPA Entity classes
│   ├── enums/          # Enum types
│   │   ├── ActivityStatus.java
│   │   ├── AuditType.java
│   │   ├── FileType.java
│   │   ├── ImplementationStatus.java
│   │   ├── NotificationEvent.java
│   │   ├── NotificationType.java
│   │   ├── ReminderType.java
│   │   ├── UserRole.java
│   │   └── WorkflowStage.java
│   ├── AuditChangeLog.java
│   ├── AuditRecommendation.java
│   ├── AuditReport.java
│   ├── Department.java
│   ├── EvidenceSubmission.java
│   ├── FileAttachment.java
│   ├── ImplementationActivity.java
│   ├── Notification.java
│   ├── Reminder.java
│   ├── User.java
│   └── WorkflowHistory.java
├── projection/          # JPA Projections for optimized queries
├── repository/          # JPA Repositories
├── service/             # Service interfaces
│   └── impl/           # Service implementations
└── ArtsBackendApplication.java
```

## Exception Handling

All exceptions are handled globally by `GlobalExceptionHandler` using `@RestControllerAdvice`.

### Available Exceptions:
- **ResourceNotFoundException** (404) - Resource not found
- **DuplicateResourceException** (409) - Duplicate resource conflict
- **BadRequestException** (400) - Invalid request
- **ValidationException** (400) - Validation errors
- **UnauthorizedException** (401) - Authentication required
- **ForbiddenException** (403) - Access denied
- **WorkflowException** (422) - Workflow state errors
- **DatabaseException** (500) - Database errors
- **InvalidRequestException** (400) - Invalid request format
- **FileStorageException** (500) - File storage errors

## API Response Format

All API responses follow this standard format:

```json
{
  "success": true/false,
  "message": "Response message",
  "data": { ... },
  "timestamp": "2024-01-01T10:00:00",
  "path": "/api/endpoint"
}
```

## Next Steps

1. Implement Repository interfaces in `repository/` package
2. Create Service interfaces in `service/` package
3. Implement Service classes in `service/impl/` package
4. Create DTOs for request/response in `dto/` package
5. Implement Controllers in `controller/` package
6. Add Projections in `projection/` package for optimized queries
7. Configure application.properties with database settings
8. Add Spring Security configuration in `config/` package
