package com.matchpointecv.matchpointecv.jogo;

import com.matchpointecv.matchpointecv.usuario.dto.UsuarioVisualizarDTO;
import java.util.List;
import lombok.Data;

@Data
public class JogoVisualizarDTO {
    private Long id;
    private String data;
    private String hora;
    private String local;
    private Integer maxParticipantes;
    private Long criadorId;
    private String tipo;
    private List<UsuarioVisualizarDTO> participantes;
}
