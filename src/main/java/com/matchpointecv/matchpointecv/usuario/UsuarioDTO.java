package com.matchpointecv.matchpointecv.usuario;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private Date dataNascimento;

    private List<Long> jogosIds;

    private List<Long> timesIds;
}
