package com.matchpointecv.matchpointecv.usuario.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String dataNascimento;

    private String cpf;
}
