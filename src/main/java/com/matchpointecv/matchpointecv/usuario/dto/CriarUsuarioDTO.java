package com.matchpointecv.matchpointecv.usuario.dto;

import com.matchpointecv.matchpointecv.role.enuns.RoleName;

public record CriarUsuarioDTO(String email, String password, RoleName role) {
}
