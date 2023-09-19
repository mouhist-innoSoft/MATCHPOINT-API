package com.matchpointecv.matchpointecv.usuario;

import java.util.List;

public interface UsuarioService {

    Usuario getById(Long id);

    List<Usuario> getAll();

    UsuarioDTO save(UsuarioDTO usuarioDTO);

}
