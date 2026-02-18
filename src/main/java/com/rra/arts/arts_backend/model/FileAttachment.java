package com.rra.arts.arts_backend.model;

import com.rra.arts.arts_backend.model.enums.FileType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Entity
@Table(name = "file_attachment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileAttachment extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evidence_submission_id")
    private EvidenceSubmission evidenceSubmission;
    
    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;
    
    @Column(name = "stored_file_name", nullable = false)
    private String storedFileName;
    
    @Column(name = "file_path", nullable = false)
    private String filePath;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "file_type", nullable = false)
    private FileType fileType;
    
    @Column(name = "file_size")
    private Long fileSize;
    
    @Column(name = "mime_type")
    private String mimeType;
    
    @Column(name = "uploaded_at", updatable = false)
    private Instant uploadedAt;
    
    @PrePersist
    protected void onFileUpload() {
        super.onCreate();
        this.uploadedAt = Instant.now();
    }
}
