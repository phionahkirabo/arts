package com.rra.arts.arts_backend.model;


import com.rra.arts.arts_backend.model.enums.AuditType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "audit_report",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_audit_report_number",
                        columnNames = "report_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditReport extends BaseEntity {

    @Column(name = "report_number", nullable = false, length = 50)
    private String reportNumber;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "scope", nullable = false, columnDefinition = "TEXT")
    private String scope;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_type", nullable = false, length = 50)
    private AuditType auditType;

    @Column(name = "report_date")
    private LocalDate reportDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_director_id")
    private User assignedDirector;
}
