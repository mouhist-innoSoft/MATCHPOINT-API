package com.matchpointecv.matchpointecv.time;

import com.matchpointecv.matchpointecv.usuario.Usuario;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TimeDTO {

    private Long id;

    private String nome;

    private Usuario capitao;

    private List<Long> integrantes;

    private List<Long> jogos;
}
