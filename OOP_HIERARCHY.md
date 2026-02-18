# ARTS Backend - OOP Entity Hierarchy

## BaseEntity (Abstract Class)

```
@MappedSuperclass
BaseEntity
├── id: String
├── createdAt: Instant
├── updatedAt: Instant
├── onCreate() - @PrePersist
└── onUpdate() - @PreUpdate
```

## Entity Inheritance Hierarchy

All entities extend `BaseEntity` following OOP principles:

```
BaseEntity (Abstract)
│
├── Department
│   ├── name: String
│   ├── code: String (unique)
│   └── description: String
│
├── User
│   ├── fullName: String
│   ├── email: String (unique)
│   ├── phone: String
│   ├── passwordHash: String
│   ├── role: UserRole (enum)
│   ├── department: Department (ManyToOne)
│   └── isActive: Boolean
│
├── AuditReport
│   ├── reportNumber: String (unique)
│   ├── title: String
│   ├── scope: String
│   ├── auditType: AuditType (enum)
│   ├── auditTeam: String
│   ├── reportDate: LocalDate
│   ├── createdBy: User (ManyToOne)
│   └── assignedDirector: User (ManyToOne)
│
├── AuditRecommendation
│   ├── findingId: String (unique)
│   ├── auditReport: AuditReport (ManyToOne)
│   ├── findingTitle: String
│   ├── findingDescription: String
│   ├── recommendation: String
│   ├── correctiveActions: String
│   ├── implementationDeadline: LocalDate
│   ├── responsibleDepartment: Department (ManyToOne)
│   ├── focalPerson: User (ManyToOne)
│   ├── responsibleStaff: User (ManyToOne)
│   ├── auditorFollowup: User (ManyToOne)
│   ├── currentStatus: ImplementationStatus (enum)
│   ├── currentWorkflowStage: WorkflowStage (enum)
│   ├── isOverdue: Boolean
│   └── createdBy: User (ManyToOne)
│
├── ImplementationActivity
│   ├── recommendation: AuditRecommendation (ManyToOne)
│   ├── description: String
│   ├── assignedTo: User (ManyToOne)
│   ├── dueDate: LocalDate
│   └── status: ActivityStatus (enum)
│
├── EvidenceSubmission
│   ├── recommendation: AuditRecommendation (ManyToOne)
│   ├── submittedBy: User (ManyToOne)
│   ├── comment: String
│   └── submissionRound: Integer
│
├── FileAttachment
│   ├── evidenceSubmission: EvidenceSubmission (ManyToOne)
│   ├── originalFileName: String
│   ├── storedFileName: String
│   ├── filePath: String
│   ├── fileType: FileType (enum)
│   ├── fileSize: Long
│   ├── mimeType: String
│   ├── uploadedAt: Instant
│   └── onFileUpload() - @PrePersist (custom)
│
├── WorkflowHistory
│   ├── recommendation: AuditRecommendation (ManyToOne)
│   ├── actionBy: User (ManyToOne)
│   ├── fromStage: WorkflowStage (enum)
│   ├── toStage: WorkflowStage (enum)
│   ├── comment: String
│   └── proposedStatus: ImplementationStatus (enum)
│
├── Notification
│   ├── recipient: User (ManyToOne)
│   ├── recommendation: AuditRecommendation (ManyToOne)
│   ├── event: NotificationEvent (enum)
│   ├── type: NotificationType (enum)
│   ├── message: String
│   └── isRead: Boolean
│
├── Reminder
│   ├── sentBy: User (ManyToOne)
│   ├── sentTo: User (ManyToOne)
│   ├── recommendation: AuditRecommendation (ManyToOne)
│   ├── reminderType: ReminderType (enum)
│   ├── message: String
│   ├── meetingDatetime: LocalDateTime
│   ├── meetingLink: String
│   └── meetingLocation: String
│
└── AuditChangeLog (Immutable)
    ├── entityType: String
    ├── entityId: String
    ├── changedBy: User (ManyToOne)
    ├── fieldName: String
    ├── oldValue: String
    ├── newValue: String
    ├── changeDescription: String
    └── onUpdate() - Overridden to prevent updates
```

## Benefits of This OOP Design

### 1. **DRY Principle (Don't Repeat Yourself)**
- Common fields (id, createdAt, updatedAt) defined once in BaseEntity
- Automatic timestamp management inherited by all entities
- Reduces code duplication across 11 entities

### 2. **Single Responsibility**
- BaseEntity handles timestamp management
- Child entities focus on their specific business logic
- Clear separation of concerns

### 3. **Maintainability**
- Changes to common fields only need to be made in BaseEntity
- Consistent behavior across all entities
- Easy to add new common fields in the future

### 4. **Consistency**
- All entities follow the same pattern
- Predictable structure for developers
- Standardized timestamp handling

### 5. **Extensibility**
- Easy to add new entities by extending BaseEntity
- Can override methods for custom behavior (e.g., FileAttachment.onFileUpload())
- Can add more common functionality to BaseEntity as needed

## Special Cases

### FileAttachment
- Extends BaseEntity but adds custom `uploadedAt` field
- Overrides `@PrePersist` to set both inherited timestamps and uploadedAt

### AuditChangeLog
- Extends BaseEntity but is immutable (audit trail)
- Overrides `@PreUpdate` to prevent modifications
- Only createdAt is meaningful (no updates allowed)

## Usage Example

```java
// Creating a new Department
Department dept = new Department();
dept.setId(UUID.randomUUID().toString());
dept.setName("Digital Transformation Department");
dept.setCode("DTD");
// createdAt and updatedAt are automatically set by BaseEntity

departmentRepository.save(dept);

// Updating a Department
dept.setDescription("Handles digital transformation initiatives");
// updatedAt is automatically updated by BaseEntity
departmentRepository.save(dept);
```
