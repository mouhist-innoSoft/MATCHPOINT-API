package com.mindflow.api.usuario.dto;

import lombok.Data;

@Data
public class UsuarioVisualizarDTO {

    private Long id;

    private String nome;

    private String email;

    private String dataNascimento;

    private String cpf;
}
