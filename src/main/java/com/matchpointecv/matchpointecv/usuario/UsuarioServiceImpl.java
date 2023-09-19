package com.matchpointecv.matchpointecv.usuario;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<Usuario> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setDataNascimento(usuarioDTO.getDataNascimento());

       return modelMapper.map(repository.save(usuario), UsuarioDTO.class) ;
    }

}
