package com.matchpointecv.matchpointecv.jogo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class JogoDTO {

    private Long id;
    private LocalDate data;
    private LocalDateTime hora;
    private String local;
    private Integer maxParticipantes;
    private Long criadorId;
}
