package com.rra.arts.arts_backend.service.impl;


import com.rra.arts.arts_backend.dto.requests.AuditReportRequestDTO;
import com.rra.arts.arts_backend.dto.responses.AuditReportResponseDTO;
import com.rra.arts.arts_backend.exception.ForbiddenException;
import com.rra.arts.arts_backend.exception.ResourceNotFoundException;
import com.rra.arts.arts_backend.exception.UnauthorizedException;
import com.rra.arts.arts_backend.model.AuditReport;
import com.rra.arts.arts_backend.model.ReportSequence;
import com.rra.arts.arts_backend.model.User;
import com.rra.arts.arts_backend.model.enums.UserRole;
import com.rra.arts.arts_backend.repository.AuditReportRepository;
import com.rra.arts.arts_backend.repository.ReportSequenceRepository;
import com.rra.arts.arts_backend.repository.UsersRepository;
import com.rra.arts.arts_backend.service.AuditReportService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
@RequiredArgsConstructor
public class AuditReportServiceImpl implements AuditReportService {

    private final AuditReportRepository auditReportRepository;
    private final ReportSequenceRepository reportSequenceRepository;
    private final UsersRepository userRepository;

    @Override
    @Transactional
    private User getAuthenticatedUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User is not authenticated");
        }

        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Authenticated user not found"));
    }


    int year = Year.now().getValue();

        ReportSequence sequence = reportSequenceRepository
                .findByYearForUpdate(year)
                .orElseGet(() -> {
                    ReportSequence newSequence = ReportSequence.builder()
                            .year(year)
                            .lastValue(0L)
                            .build();
                    return reportSequenceRepository.save(newSequence);
                });

        sequence.setLastValue(sequence.getLastValue() + 1);
        reportSequenceRepository.save(sequence);

        String reportNumber = String.format("AR-%d-%04d", year, sequence.getLastValue());

        AuditReport report = AuditReport.builder()
                .reportNumber(reportNumber)
                .title(requestDTO.getTitle())
                .scope(requestDTO.getScope())
                .auditType(requestDTO.getAuditType())
                .reportDate(
                        requestDTO.getReportDate() != null
                                ? requestDTO.getReportDate()
                                : LocalDate.now()
                )
                .createdBy(currentUser)
                .build();

        AuditReport saved = auditReportRepository.save(report);

        return AuditReportMapper.toResponse(saved);
    }
}

