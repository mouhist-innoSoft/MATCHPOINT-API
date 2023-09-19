package com.matchpointecv.matchpointecv.usuario;

import java.util.List;

public interface UsuarioService {

    Usuario getById(Long id);

    List<Usuario> getAll();

    List<UsuarioDTO> getAllByIds(List<Long> ids);

    UsuarioDTO save(UsuarioDTO usuarioDTO);

}
