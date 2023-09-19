package com.matchpointecv.matchpointecv.usuario;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private Date dataNascimento;

    private Set<Long> jogosIds;

    private Set<Long> timesIds;
}
