package com.matchpointecv.matchpointecv.jogo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoJogoEnum {

    PUB("Público"), PRI("Privado");

    private String value;
}
