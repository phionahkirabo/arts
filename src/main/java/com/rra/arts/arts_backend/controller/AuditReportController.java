package com.rra.arts.arts_backend.controller;

import com.rra.arts.arts_backend.dto.requests.AuditReportRequestDTO;
import com.rra.arts.arts_backend.dto.responses.AuditReportResponseDTO;
import com.rra.arts.arts_backend.service.AuditReportService;
import com.rra.arts.arts_backend.dto.StandardApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-reports")
@RequiredArgsConstructor
@Tag(name = "Audit Reports", description = "Operations for managing audit reports")
public class AuditReportController {

    private final AuditReportService auditReportService;

    // ----------------------------
    // Create new Audit Report
    // ----------------------------
    @PostMapping
    @Operation(summary = "Create a new Audit Report")
    public ResponseEntity<StandardApiResponse<AuditReportResponseDTO>> createReport(
            @Valid @RequestBody AuditReportRequestDTO request
    ) {
        AuditReportResponseDTO responseDTO = auditReportService.createReport(request);
        return ResponseEntity.ok(
                StandardApiResponse.<AuditReportResponseDTO>builder()
                        .success(true)
                        .message("Audit report created successfully")
                        .data(responseDTO)
                        .build()
        );
    }

    // ----------------------------
    // Get all Audit Reports
    // ----------------------------
    @GetMapping
    @Operation(summary = "Get all Audit Reports")
    public ResponseEntity<StandardApiResponse<List<AuditReportResponseDTO>>> getAllReports() {
        List<AuditReportResponseDTO> reports = auditReportService.getAllReports();
        return ResponseEntity.ok(
                StandardApiResponse.<List<AuditReportResponseDTO>>builder()
                        .success(true)
                        .message("Audit reports retrieved successfully")
                        .data(reports)
                        .build()
        );
    }

    // ----------------------------
    // Get Audit Report by ID
    // ----------------------------
    @GetMapping("/{id}")
    @Operation(summary = "Get Audit Report by ID")
    public ResponseEntity<StandardApiResponse<AuditReportResponseDTO>> getReportById(@PathVariable Long id) {
        AuditReportResponseDTO report = auditReportService.getReportById(id);
        return ResponseEntity.ok(
                StandardApiResponse.<AuditReportResponseDTO>builder()
                        .success(true)
                        .message("Audit report retrieved successfully")
                        .data(report)
                        .build()
        );
    }

    // ----------------------------
    // Assign Director
    // ----------------------------
    @PutMapping("/{reportId}/assign-director/{directorId}")
    @Operation(summary = "Assign a Director to an Audit Report")
    public ResponseEntity<StandardApiResponse<AuditReportResponseDTO>> assignDirector(
            @PathVariable Long reportId,
            @PathVariable Long directorId
    ) {
        AuditReportResponseDTO updatedReport = auditReportService.assignDirector(reportId, directorId);
        return ResponseEntity.ok(
                StandardApiResponse.<AuditReportResponseDTO>builder()
                        .success(true)
                        .message("Director assigned successfully")
                        .data(updatedReport)
                        .build()
        );
    }

    // ----------------------------
    // Update Audit Report
    // ----------------------------
    @PutMapping("/{id}")
    @Operation(summary = "Update an Audit Report by ID")
    public ResponseEntity<StandardApiResponse<AuditReportResponseDTO>> updateReport(
            @PathVariable Long id,
            @Valid @RequestBody AuditReportResponseDTO updateRequest
    ) {
        AuditReportResponseDTO updated = auditReportService.updateById(id, updateRequest);
        return ResponseEntity.ok(
                StandardApiResponse.<AuditReportResponseDTO>builder()
                        .success(true)
                        .message("Audit report updated successfully")
                        .data(updated)
                        .build()
        );
    }
}
