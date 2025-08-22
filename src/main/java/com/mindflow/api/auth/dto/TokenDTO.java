package com.mindflow.api.auth.dto;

import com.mindflow.api.usuario.dto.UsuarioDTO;

public record TokenDTO(
        String token,
        UsuarioDTO usuario

) {
}