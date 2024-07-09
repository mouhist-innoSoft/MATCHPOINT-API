package com.matchpointecv.matchpointecv.exception;

import java.io.Serial;

public class RecordNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(String nomeRegistro, Long id) {
        super(nomeRegistro + " não encontrado com id: " + id);
    }

}
