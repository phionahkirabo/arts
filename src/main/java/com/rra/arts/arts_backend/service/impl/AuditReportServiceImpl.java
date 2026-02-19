package com.rra.arts.arts_backend.service.impl;


import com.rra.arts.arts_backend.dto.requests.AuditReportRequestDTO;
import com.rra.arts.arts_backend.dto.responses.AuditReportResponseDTO;

import com.rra.arts.arts_backend.exception.ForbiddenException;
import com.rra.arts.arts_backend.exception.ResourceNotFoundException;
import com.rra.arts.arts_backend.exception.UnauthorizedException;
import com.rra.arts.arts_backend.mapper.AuditReportMapper;
import com.rra.arts.arts_backend.model.AuditReport;
import com.rra.arts.arts_backend.model.ReportsSequencing;
import com.rra.arts.arts_backend.model.User;
import com.rra.arts.arts_backend.model.enums.UserRole;
import com.rra.arts.arts_backend.repository.AuditReportRepository;
import com.rra.arts.arts_backend.repository.ReportSequenceRepository;
import com.rra.arts.arts_backend.repository.UsersRepository;
import com.rra.arts.arts_backend.service.AuditReportService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service

public class AuditReportServiceImpl implements AuditReportService {
    private final AuditReportRepository auditReportRepository;
    private final ReportSequenceRepository reportSequenceRepository;
    private final UsersRepository usersRepository;



    public AuditReportServiceImpl(AuditReportRepository auditReportRepository, ReportSequenceRepository reportSequenceRepository, UsersRepository usersRepository ) {
        this.auditReportRepository = auditReportRepository;
        this.reportSequenceRepository = reportSequenceRepository;
        this.usersRepository = usersRepository;

    }


    @Override
    @Transactional
    public AuditReportResponseDTO createReport(AuditReportRequestDTO request) {
        // 1️⃣ Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User must be logged in to create an audit report");
        }

        String email = authentication.getName();
        User currentUser = usersRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Logged-in user not found"));

        // 2️⃣ Validate role
        if (currentUser.getRole() != UserRole.INTERNAL_AUDITOR) {
            throw new ForbiddenException("Only INTERNAL_AUDITOR can create audit reports");
        }

        // 3️⃣ Generate report number (concurrency-safe)
        Integer year = Year.now().getValue();
        ReportsSequencing sequence = reportSequenceRepository
                .findBySequenceYearForUpdate(year)
                .orElseGet(() -> {
                    ReportsSequencing newSequence = ReportsSequencing.builder()
                            .sequenceYear(year)
                            .lastValue(0L)
                            .build();
                    return reportSequenceRepository.saveAndFlush(newSequence);
                });

        sequence.setLastValue(sequence.getLastValue() + 1);
        reportSequenceRepository.save(sequence);


        String reportNumber = String.format("AR-%d-%04d", year, sequence.getLastValue());

        // 4️⃣ Map DTO → Entity
        AuditReport auditReport = AuditReport.builder()
                .title(request.getTitle())
                .scope(request.getScope())
                .auditType(request.getAuditType())
                .reportDate(request.getReportDate())
                .createdBy(currentUser)
                .reportNumber(reportNumber)
                .build();

        // 5️⃣ Save entity
        AuditReport savedReport = auditReportRepository.save(auditReport);

        // 6️⃣ Map Entity → Response DTO

        return AuditReportMapper.toResponse(savedReport);
    }

    @Override
    public List<AuditReportResponseDTO> getAllReports() {
        List<AuditReport> reports = auditReportRepository.findAll();
        return reports.stream()
                .map(AuditReportMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public AuditReportResponseDTO assignDirector(Long reportId, Long directorId) {
        AuditReport report = auditReportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Audit report not found"));

        User director = usersRepository.findById(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found"));

        if (director.getRole() != UserRole.DIRECTOR_IAU) {
            throw new ForbiddenException("Assigned user is not a Director");
        }

        report.setAssignedDirector(director);
        AuditReport updatedReport = auditReportRepository.save(report);

        return AuditReportMapper.toResponse(updatedReport);
    }
    @Override
    public AuditReportResponseDTO getReportById(Long id) {
        AuditReport report = auditReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit report not found"));
        return AuditReportMapper.toResponse(report);
    }



    @Override
    @Transactional
    public AuditReportResponseDTO updateById(Long id, AuditReportResponseDTO updateRequest) {
        AuditReport report = auditReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit report not found"));

        report.setTitle(updateRequest.getTitle());
        report.setScope(updateRequest.getScope());
        report.setAuditType(updateRequest.getAuditType());
        report.setReportDate(updateRequest.getReportDate());

        AuditReport updated = auditReportRepository.save(report);
        return AuditReportMapper.toResponse(updated);
    }
}

