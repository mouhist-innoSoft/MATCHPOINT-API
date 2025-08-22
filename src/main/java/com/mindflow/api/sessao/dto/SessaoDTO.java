package com.mindflow.api.sessao.dto;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record SessaoDTO(
        Long id,
        Long consulta_id,
        String resumoAtendimento,
        String observacoes,
        OffsetDateTime dataRegistro
) {
}
