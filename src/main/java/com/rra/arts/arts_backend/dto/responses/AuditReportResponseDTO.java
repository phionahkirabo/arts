package com.rra.arts.arts_backend.dto.responses;

import com.rra.arts.arts_backend.model.enums.AuditType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class AuditReportResponseDTO {

    private Long id;
    private String reportNumber;
    private String title;
    private String scope;
    private AuditType auditType;
    private LocalDate reportDate;
    private String createdBy;
    private String assignedDirector;
}
