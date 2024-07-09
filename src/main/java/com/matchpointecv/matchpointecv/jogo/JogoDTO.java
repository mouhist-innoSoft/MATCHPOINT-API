package com.matchpointecv.matchpointecv.jogo;

import java.util.List;
import lombok.Data;

@Data
public class JogoDTO {

    private Long id;
    private String data;
    private String hora;
    private String local;
    private Integer maxParticipantes;
    private Long criadorId;
    private String tipo;
    private List<Long> idsParticipantes;
}
