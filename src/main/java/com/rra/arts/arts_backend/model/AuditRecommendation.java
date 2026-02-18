package com.rra.arts.arts_backend.model;

import com.rra.arts.arts_backend.model.enums.ImplementationStatus;
import com.rra.arts.arts_backend.model.enums.WorkflowStage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "audit_recommendation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditRecommendation extends BaseEntity {
    
    @Column(name = "finding_id", unique = true, nullable = false)
    private String findingId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audit_report_id")
    private AuditReport auditReport;
    
    @Column(name = "finding_title", nullable = false)
    private String findingTitle;
    
    @Column(name = "finding_description", columnDefinition = "TEXT", nullable = false)
    private String findingDescription;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String recommendation;
    
    @Column(name = "corrective_actions", columnDefinition = "TEXT")
    private String correctiveActions;
    
    @Column(name = "implementation_deadline", nullable = false)
    private LocalDate implementationDeadline;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_department_id")
    private Department responsibleDepartment;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "focal_person_id")
    private User focalPerson;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_staff_id")
    private User responsibleStaff;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditor_followup_id")
    private User auditorFollowup;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "current_status")
    private ImplementationStatus currentStatus = ImplementationStatus.NOT_IMPLEMENTED;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "current_workflow_stage")
    private WorkflowStage currentWorkflowStage = WorkflowStage.CREATED;
    
    @Column(name = "is_overdue")
    private Boolean isOverdue = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;
}
