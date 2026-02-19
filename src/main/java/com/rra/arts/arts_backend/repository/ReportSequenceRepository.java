package com.rra.arts.arts_backend.repository;


import com.rra.arts.arts_backend.model.ReportsSequencing;
import org.springframework.data.jpa.repository.*;
        import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ReportSequenceRepository extends JpaRepository<ReportsSequencing, Long> {

    @Query(
            value = "SELECT * FROM report_sequence WHERE sequence_year = :sequenceYear FOR UPDATE",
            nativeQuery = true
    )
    Optional<ReportsSequencing> findBySequenceYearForUpdate(
            @Param("sequenceYear") Integer sequenceYear
    );

    Optional<ReportsSequencing> findBySequenceYear(Integer sequenceYear);
}
