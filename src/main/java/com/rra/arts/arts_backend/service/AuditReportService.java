package com.rra.arts.arts_backend.service;

import com.rra.arts.arts_backend.dto.requests.AuditReportRequestDTO;
import com.rra.arts.arts_backend.dto.responses.AuditReportResponseDTO;

import java.util.List;

public interface AuditReportService {


    AuditReportResponseDTO createReport(AuditReportRequestDTO request) ;

    List<AuditReportResponseDTO> getAllReports();
    AuditReportResponseDTO assignDirector(Long reportId, Long directorId);

    AuditReportResponseDTO getReportById(Long id);
    AuditReportResponseDTO updateById(Long id,AuditReportResponseDTO updateRequest);
}


