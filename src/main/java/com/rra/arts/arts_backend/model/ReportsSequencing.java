package com.rra.arts.arts_backend.model;


import jakarta.persistence.*;
import lombok.*;


@Entity

@Table(name = "report_sequence", uniqueConstraints = @UniqueConstraint(columnNames = "year"))


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportsSequencing extends BaseEntity{

    @Column(name = "sequence_year", nullable = false, unique = true)
    private Integer sequenceYear;

    @Column(name = "last_sequence_value", nullable = false)
    private Long lastValue;
}

