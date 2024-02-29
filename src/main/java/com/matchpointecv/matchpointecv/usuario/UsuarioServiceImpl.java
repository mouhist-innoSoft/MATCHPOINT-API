package com.matchpointecv.matchpointecv.usuario;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.matchpointecv.matchpointecv.exception.RecordNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UsuarioDTO getById(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Usuário", id));

        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public List<UsuarioDTO> getAllByIds(List<Long> ids) {
        List<Usuario> usuarios = repository.findAllByIdIn(ids);

        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .toList();
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usrCpf = repository.findByCpf(usuarioDTO.getCpf());

        if (usrCpf == null) {
            String passwordHashred = BCrypt.withDefaults()
                    .hashToString(12, usuarioDTO.getSenha().toCharArray());

            Usuario usuario = new Usuario();
            usuario.setId(usuarioDTO.getId());
            usuario.setNome(usuarioDTO.getNome());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setSenha(passwordHashred);
            usuario.setDataNascimento(usuarioDTO.getDataNascimento());
            usuario.setCpf(usuarioDTO.getCpf());

            Usuario userSaved = repository.save(usuario);

            return modelMapper.map(userSaved, UsuarioDTO.class);
        } else {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

    }

}
