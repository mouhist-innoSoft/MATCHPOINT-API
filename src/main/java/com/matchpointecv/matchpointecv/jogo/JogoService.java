package com.matchpointecv.matchpointecv.jogo;

public interface JogoService {

    Jogo getById(Long id);

    JogoDTO save(JogoDTO jogoDTO);
}
