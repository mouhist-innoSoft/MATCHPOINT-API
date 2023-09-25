package com.matchpointecv.matchpointecv.jogo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class JogoDTO {

    private Long id;
    private LocalDate data;
    private LocalDateTime hora;
    private String local;
    private Integer maxParticipantes;
    private Long criadorId;
    private List<Long> participantesIds;
    private List<Long> timesIds;
}
