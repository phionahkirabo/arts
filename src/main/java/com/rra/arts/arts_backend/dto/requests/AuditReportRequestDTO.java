package com.rra.arts.arts_backend.dto.requests;

import com.rra.arts.arts_backend.model.enums.AuditType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuditReportRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String scope;

    @NotNull
    private AuditType auditType;

    private LocalDate reportDate;
}
