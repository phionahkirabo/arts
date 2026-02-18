package com.rra.arts.arts_backend.model;

import com.rra.arts.arts_backend.model.enums.AuditType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "audit_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditReport extends BaseEntity {
    
    @Column(name = "report_number", unique = true, nullable = false)
    private String reportNumber;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String scope;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "audit_type", nullable = false)
    private AuditType auditType;
    
    @Column(name = "audit_team", columnDefinition = "TEXT")
    private String auditTeam;
    
    @Column(name = "report_date")
    private LocalDate reportDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_director_id")
    private User assignedDirector;
}
