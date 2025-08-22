package com.mindflow.api.consulta.dto;

import com.mindflow.api.consulta.ConsultaStatus;
import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record ConsultaDTO(
        Long id,
        Long paciente_id,
        Long psicologo_id,
        OffsetDateTime dataHora,
        ConsultaStatus status,
        String observacoes,
        OffsetDateTime createdAt
) {
}
