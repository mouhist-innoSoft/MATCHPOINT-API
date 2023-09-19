package com.matchpointecv.matchpointecv.jogo;

import java.util.Optional;

public interface JogoService {

    Optional<Jogo> getById(Long id);

    JogoDTO save(JogoDTO jogoDTO);
}
