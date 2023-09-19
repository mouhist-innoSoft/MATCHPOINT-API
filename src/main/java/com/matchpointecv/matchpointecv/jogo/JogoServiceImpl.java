package com.matchpointecv.matchpointecv.jogo;

import com.matchpointecv.matchpointecv.usuario.Usuario;
import com.matchpointecv.matchpointecv.usuario.UsuarioService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JogoServiceImpl implements JogoService{

    @Autowired
    private JogoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioService usuarioService;

    public Optional<Jogo> getById(Long id) {
        return repository.findById(id);
    }

    public JogoDTO save(JogoDTO jogoDTO) {
        Optional<Usuario> usuarioOptional  =  usuarioService.getById(jogoDTO.getCriadorId());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            Jogo jogo = new Jogo();
            jogo.setData(jogoDTO.getData());
            jogo.setHora(jogoDTO.getHora());
            jogo.setLocal(jogoDTO.getLocal());
            jogo.setMaxParticipantes(jogoDTO.getMaxParticipantes());
            jogo.setCriadorId(usuario);


           return modelMapper.map(repository.save(jogo), JogoDTO.class);

        }
        return null;
    }
}
