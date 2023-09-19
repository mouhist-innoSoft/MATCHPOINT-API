package com.matchpointecv.matchpointecv.jogo;

import java.util.List;

public interface JogoService {

    Jogo getById(Long id);

    JogoDTO save(JogoDTO jogoDTO);

    List<JogoDTO> getAllByIds(List<Long> ids);
}
