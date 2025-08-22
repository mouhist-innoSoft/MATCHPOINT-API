package com.mindflow.api.paciente.dto;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record PacienteDTO(
        Long id,
        String nome,
        String contato,
        String dataNascimento,
        Long psicologo_id,
        OffsetDateTime createdAt,
        String observacoes
) {
}
