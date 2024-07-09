package com.matchpointecv.matchpointecv.time;

import lombok.Data;

import java.util.List;

@Data
public class TimeDTO {

    private Long id;

    private String nome;

    private Long capitao;

    private List<Long> integrantes;

    private List<Long> jogos;
}
