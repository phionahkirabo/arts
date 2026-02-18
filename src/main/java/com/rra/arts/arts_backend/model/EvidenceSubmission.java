package com.rra.arts.arts_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "evidence_submission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvidenceSubmission extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommendation_id")
    private AuditRecommendation recommendation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitted_by")
    private User submittedBy;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;
    
    @Column(name = "submission_round")
    private Integer submissionRound = 1;
}
