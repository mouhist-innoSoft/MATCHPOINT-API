package com.matchpointecv.matchpointecv.usuario;

import lombok.Data;

@Data
public class UsuarioVisualizarDTO {

    private Long id;

    private String nome;

    private String email;

    private String dataNascimento;

    private String cpf;
}
