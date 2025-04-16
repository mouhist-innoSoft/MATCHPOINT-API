package com.matchpointecv.matchpointecv.usuario.dto;

import java.util.Set;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private String dataNascimento;

    private String cpf;

    private Set<String> roles;
}
