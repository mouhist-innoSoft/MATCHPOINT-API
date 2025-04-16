package com.matchpointecv.matchpointecv.usuario.dto;

public record CriarUsuarioDTO(
        String email,
        String password,
        String nome,
        String dataNascimento,
        String cpf,
        String role
) {
}
