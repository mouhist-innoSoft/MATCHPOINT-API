package com.matchpointecv.matchpointecv.usuario;

import java.util.Date;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private Date dataNascimento;
}
