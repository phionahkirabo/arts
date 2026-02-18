package com.rra.arts.arts_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Entity
@Table(name = "audit_change_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditChangeLog extends BaseEntity {
    
    @Column(name = "entity_type", nullable = false)
    private String entityType;
    
    @Column(name = "entity_id", nullable = false)
    private String entityId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by")
    private User changedBy;
    
    @Column(name = "field_name")
    private String fieldName;
    
    @Column(name = "old_value", columnDefinition = "TEXT")
    private String oldValue;
    
    @Column(name = "new_value", columnDefinition = "TEXT")
    private String newValue;
    
    @Column(name = "change_description", columnDefinition = "TEXT")
    private String changeDescription;
    
    @Override
    @PreUpdate
    protected void onUpdate() {
        // Prevent updates to audit log
    }
}
