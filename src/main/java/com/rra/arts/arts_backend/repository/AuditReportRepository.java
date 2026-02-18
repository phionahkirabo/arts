package com.rra.arts.arts_backend.repository;

import com.rra.arts.arts_backend.model.AuditReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditReportRepository extends JpaRepository<AuditReport, Long> {

    boolean existsByReportNumber(String reportNumber);
}
