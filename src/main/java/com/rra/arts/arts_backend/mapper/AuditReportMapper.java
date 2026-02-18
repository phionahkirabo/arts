package com.rra.arts.arts_backend.mapper;

import com.rra.arts.arts_backend.dto.responses.AuditReportResponseDTO;
import com.rra.arts.arts_backend.model.AuditReport;

public class AuditReportMapper {

    public static AuditReportResponseDTO toResponse(AuditReport report) {

        return AuditReportResponseDTO.builder()
                .id(report.getId())
                .reportNumber(report.getReportNumber())
                .title(report.getTitle())
                .scope(report.getScope())
                .auditType(report.getAuditType())
                .reportDate(report.getReportDate())
                .createdBy(report.getCreatedBy().getFullName())
                .assignedDirector(
                        report.getAssignedDirector() != null
                                ? report.getAssignedDirector().getFullName()
                                : null
                )
                .build();
    }
}
