package com.matchpointecv.matchpointecv.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> getById(Long id);

    List<Usuario> getAll();

    UsuarioDTO save(UsuarioDTO usuarioDTO);

}
