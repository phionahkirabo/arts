# Audit Report Module Specification

Build the Audit Report module using the following architecture and rules.

---

## 1️⃣ Functional Flow

Implement the audit report workflow with this exact order:

1. Internal Auditor creates an Audit Report.
2. The system automatically generates the report number.
3. The Director can be assigned later (not mandatory at creation).
4. Audit Recommendations will belong to the Audit Report (One-to-Many relationship).

---

## 2️⃣ Report Number Generation (Option C – Business-Based Format)

The system MUST generate the report number automatically using this format:


**Examples:**

- AR-2026-0001
- AR-2026-0002

**Rules:**

- Prefix must be `AR`
- `YEAR` must be the current system year
- Sequence must reset every year
- Sequence must be zero-padded to 4 digits
- Must be unique
- Must be concurrency-safe
- Must **NOT** rely on counting records

---

## 3️⃣ Concurrency-Safe Implementation

**Do NOT** generate sequence using count queries.

Instead:

1. Create a new entity: `ReportSequence`
2. Fields:
    - `year` (unique)
    - `lastValue`

**Logic:**

1. Fetch `ReportSequence` by year
2. If it does not exist → create it with `lastValue = 0`
3. Lock the row using `PESSIMISTIC_WRITE`
4. Increment `lastValue`
5. Save
6. Generate formatted report number
7. Use `@Transactional` to ensure atomic operation

---

## 4️⃣ AuditReport Entity Requirements

**Fields:**

- `id` (UUID, primary key)
- `reportNumber` (unique, not null)
- `title` (not null)
- `scope` (text, not null)
- `auditType` (enum, stored as STRING)
- `reportDate` (LocalDate)
- `createdBy` (ManyToOne → User, not null)
- `assignedDirector` (ManyToOne → User, nullable)
- `createdAt`
- `updatedAt`

**Constraints:**

- Unique constraint on `report_number`
- Use `EnumType.STRING`
- Use LAZY loading for relationships

---

## 5️⃣ Service Layer Requirements

Create: `AuditReportService`

**Method:** `createAuditReport()`

Inside service:

1. Validate user role is `INTERNAL_AUDITOR`
2. Generate report number using sequence table
3. Save `AuditReport`
4. Return DTO

---

## 6️⃣ Architecture Requirements

- Use clean separation: **Controller → Service → Repository**
- No business logic in the controller
- No report number generation in the controller
- Use DTO for request/response
- Use proper exception handling

---

## 7️⃣ Important Notes

- Director assignment must be a **separate update operation**
- Report creation must **not require director**
- Recommendation entity must reference `AuditReport` (ManyToOne)
- Design must be extensible for future reporting features

---
## 8️⃣ Developer Reminders & Best Practices

While implementing the Audit Report module, ensure the following:

### ✅ 1. Use Global Exception Handling

- All exceptions should be captured by `GlobalExceptionHandler` (package `com.rra.arts.arts_backend.exception`).
- Common exceptions include:
    - `ResourceNotFoundException` → 404 NOT FOUND
    - `DuplicateResourceException` → 409 CONFLICT
    - `BadRequestException` / `ValidationException` → 400 BAD REQUEST
    - `UnauthorizedException` → 401 UNAUTHORIZED
    - `ForbiddenException` → 403 FORBIDDEN
    - `WorkflowException` → 422 UNPROCESSABLE ENTITY
    - `DatabaseException` → 500 INTERNAL SERVER ERROR
    - `FileStorageException` → 500 INTERNAL SERVER ERROR
    - Fallback for generic `Exception` → 500 INTERNAL SERVER ERROR

> Reminder: Always validate inputs and return clear, descriptive error messages.

---

### ✅ 2. Do NOT expose entities in controllers

- Use **DTOs** for all request and response payloads.
- Map entities to DTOs and DTOs back to entities using a **mapper class**.
- Example: `AuditReportRequestDTO` → `AuditReport` entity  
  `AuditReport` entity → `AuditReportResponseDTO`

> This ensures encapsulation and prevents leaking internal database structure to clients.

---

### ✅ 3. Use Service Layer for all business logic

- Controllers must only handle HTTP requests/responses.
- Business logic (validation, sequence generation, audit number creation, saving, notifications) must reside in the **service implementation** (`AuditReportServiceImpl`).
- Example order in service:
    1. Validate user role is `INTERNAL_AUDITOR`
    2. Generate report number via `ReportSequence`
    3. Map DTO to entity
    4. Save entity
    5. Map entity to response DTO
    6. Return wrapped in `StandardApiResponse`

> Service layer centralizes logic, simplifies testing, and ensures consistency across controllers.

---

### ✅ 4. Always wrap responses in `StandardApiResponse`

- Successful creation, update, or retrieval responses must use `StandardApiResponse`.
- Example:

```java
return ResponseEntity.ok(
    StandardApiResponse.<AuditReportResponseDTO>builder()
        .success(true)
        .message("Audit report created successfully")
        .data(auditReportResponseDTO)
        .timestamp(LocalDateTime.now())
        .path(request.getDescription(false))
        .build()
);


**End of specification**
