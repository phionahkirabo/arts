package com.rra.arts.arts_backend.repository;


import com.rra.arts.arts_backend.model.ReportSequence;
import org.springframework.data.jpa.repository.*;
        import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface ReportSequenceRepository extends JpaRepository<ReportSequence, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM ReportSequence r WHERE r.year = :year")
    Optional<ReportSequence> findByYearForUpdate(@Param("year") Integer year);

    Optional<ReportSequence> findByYear(Integer year);
}
