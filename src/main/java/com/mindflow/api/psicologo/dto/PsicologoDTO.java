package com.mindflow.api.psicologo.dto;

import lombok.Builder;

@Builder
public record PsicologoDTO(
        Long id,
        Long usuario_id,
        String crp,
        String especialidade
) {
}
