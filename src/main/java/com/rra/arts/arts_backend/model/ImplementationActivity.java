package com.rra.arts.arts_backend.model;

import com.rra.arts.arts_backend.model.enums.ActivityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "implementation_activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImplementationActivity extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommendation_id")
    private AuditRecommendation recommendation;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    private User assignedTo;
    
    @Column(name = "due_date")
    private LocalDate dueDate;
    
    @Enumerated(EnumType.STRING)
    private ActivityStatus status = ActivityStatus.PENDING;
}
