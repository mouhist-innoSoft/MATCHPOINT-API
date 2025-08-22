package com.mindflow.api.usuario.dto;

public record CriarUsuarioDTO(
        String email,
        String senha,
        String nome,
        String dataNascimento,
        String cpf,
        String role
) {
}
