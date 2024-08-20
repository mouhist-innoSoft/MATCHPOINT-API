package com.matchpointecv.matchpointecv.convite;


import com.matchpointecv.matchpointecv.exception.RecordNotFoundException;
import com.matchpointecv.matchpointecv.jogo.Jogo;
import com.matchpointecv.matchpointecv.jogo.JogoService;
import com.matchpointecv.matchpointecv.usuario.Usuario;
import com.matchpointecv.matchpointecv.usuario.dto.UsuarioDTO;
import com.matchpointecv.matchpointecv.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ConviteServiceImpl implements ConviteService {

    @Autowired
    private ConviteRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JogoService jogoService;

    public Convite getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Convite", id));
    }

    public ConviteDTO save(ConviteDTO conviteDTO) {
        UsuarioDTO usuarioRemetenteDto = usuarioService.getById(conviteDTO.getRemetente());
        Usuario usuarioRemetente = modelMapper.map(usuarioRemetenteDto, Usuario.class);
        Jogo jogo = jogoService.getById(conviteDTO.getJogo());



        if (Objects.nonNull(usuarioRemetente) && Objects.nonNull(jogo)) {

            Convite convite = new Convite();
            convite.setRemetente(usuarioRemetente);
            convite.setJogo(jogo);
            convite.setStatus(conviteDTO.getStatus());


            return modelMapper.map(repository.save(convite), ConviteDTO.class);

        }

        return null;

    }

}
