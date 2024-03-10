package com.matchpointecv.matchpointecv.jogo;

import com.matchpointecv.matchpointecv.jogo.jogoUsuario.ConfirmarcaoPresencaDTO;
import java.util.List;

public interface JogoService {

    Jogo getById(Long id);

    JogoDTO save(JogoDTO jogoDTO);

    boolean confirmarPresenca(ConfirmarcaoPresencaDTO confirmarcaoPresencaDTO);

    List<JogoDTO> getAllByIds(List<Long> ids);
}
