package com.matchpointecv.matchpointecv.usuario;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO getById(Long id);

    List<Usuario> getAll();

    List<UsuarioVisualizarDTO> getAllByIds(List<Long> ids);

    boolean save(UsuarioDTO usuarioDTO);

}
