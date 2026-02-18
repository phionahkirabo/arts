package com.rra.arts.arts_backend.model;


import jakarta.persistence.*;
        import lombok.*;

@Entity
@Table(
        name = "report_sequence",
        uniqueConstraints = @UniqueConstraint(columnNames = "year")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer year;

    @Column(nullable = false)
    private Long lastValue;
}

