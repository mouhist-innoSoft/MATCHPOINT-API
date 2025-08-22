package com.mindflow.api.usuario;

import com.mindflow.api.auth.dto.CredenciaisDTO;
import com.mindflow.api.auth.dto.TokenDTO;
import com.mindflow.api.usuario.dto.CriarUsuarioDTO;
import com.mindflow.api.usuario.dto.UsuarioDTO;
import com.mindflow.api.usuario.dto.UsuarioVisualizarDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO getById(Long id);

    List<Usuario> getAll();

    List<UsuarioVisualizarDTO> getAllByIds(List<Long> ids);

    boolean save(CriarUsuarioDTO criarUsuarioDTO);

    public TokenDTO autenticarUsuario(CredenciaisDTO credenciaisDTO);

}
