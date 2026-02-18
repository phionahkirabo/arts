package com.rra.arts.arts_backend.model;

import com.rra.arts.arts_backend.model.enums.ImplementationStatus;
import com.rra.arts.arts_backend.model.enums.WorkflowStage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workflow_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowHistory extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommendation_id")
    private AuditRecommendation recommendation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_by")
    private User actionBy;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "from_stage")
    private WorkflowStage fromStage;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "to_stage", nullable = false)
    private WorkflowStage toStage;
    
    @Column(columnDefinition = "TEXT")
    private String comment;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "proposed_status")
    private ImplementationStatus proposedStatus;
}
