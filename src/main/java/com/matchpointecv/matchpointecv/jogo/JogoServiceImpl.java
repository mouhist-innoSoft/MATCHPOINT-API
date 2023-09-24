package com.matchpointecv.matchpointecv.jogo;

import com.matchpointecv.matchpointecv.exception.RecordNotFoundException;
import com.matchpointecv.matchpointecv.usuario.Usuario;
import com.matchpointecv.matchpointecv.usuario.UsuarioDTO;
import com.matchpointecv.matchpointecv.usuario.UsuarioService;

import java.util.List;
import java.util.Objects;
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

    public Jogo getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Jogo", id));
    }

    public JogoDTO save(JogoDTO jogoDTO) {
        UsuarioDTO usuarioCriadorDTO = usuarioService.getById(jogoDTO.getCriadorId());
        Usuario usuarioCriador = modelMapper.map(usuarioCriadorDTO, Usuario.class);

        if (Objects.nonNull(usuarioCriador)) {

            Jogo jogo = new Jogo();
            jogo.setData(jogoDTO.getData());
            jogo.setHora(jogoDTO.getHora());
            jogo.setLocal(jogoDTO.getLocal());
            jogo.setMaxParticipantes(jogoDTO.getMaxParticipantes());
            jogo.setCriadorId(usuarioCriador);


           return modelMapper.map(repository.save(jogo), JogoDTO.class);

        }
        return null;
    }

    public List<JogoDTO> getAllByIds(List<Long> ids) {
        List<Jogo> jogos = repository.findAllByIdIn(ids);

        return jogos.stream()
                .map(jogo -> modelMapper.map(jogo, JogoDTO.class))
                .toList();
    }
}
