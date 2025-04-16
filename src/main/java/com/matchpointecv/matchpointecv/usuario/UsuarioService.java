package com.matchpointecv.matchpointecv.usuario;

import com.matchpointecv.matchpointecv.auth.dto.CredenciaisDTO;
import com.matchpointecv.matchpointecv.auth.dto.TokenDTO;
import com.matchpointecv.matchpointecv.usuario.dto.CriarUsuarioDTO;
import com.matchpointecv.matchpointecv.usuario.dto.UsuarioDTO;
import com.matchpointecv.matchpointecv.usuario.dto.UsuarioVisualizarDTO;
import java.util.List;

public interface UsuarioService {

    UsuarioDTO getById(Long id);

    List<Usuario> getAll();

    List<UsuarioVisualizarDTO> getAllByIds(List<Long> ids);

    boolean save(CriarUsuarioDTO criarUsuarioDTO);

    public TokenDTO autenticarUsuario(CredenciaisDTO credenciaisDTO);

}
