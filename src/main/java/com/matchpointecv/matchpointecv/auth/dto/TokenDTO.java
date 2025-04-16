package com.matchpointecv.matchpointecv.auth.dto;

import com.matchpointecv.matchpointecv.usuario.dto.UsuarioDTO;

public record TokenDTO(
        String token,
        UsuarioDTO usuario

) {
}