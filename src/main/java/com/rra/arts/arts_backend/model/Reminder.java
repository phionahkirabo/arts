package com.rra.arts.arts_backend.model;

import com.rra.arts.arts_backend.model.enums.ReminderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "reminder")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reminder extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sent_by")
    private User sentBy;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sent_to")
    private User sentTo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommendation_id")
    private AuditRecommendation recommendation;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "reminder_type", nullable = false)
    private ReminderType reminderType;
    
    @Column(columnDefinition = "TEXT")
    private String message;
    
    @Column(name = "meeting_datetime")
    private LocalDateTime meetingDatetime;
    
    @Column(name = "meeting_link")
    private String meetingLink;
    
    @Column(name = "meeting_location")
    private String meetingLocation;
}
