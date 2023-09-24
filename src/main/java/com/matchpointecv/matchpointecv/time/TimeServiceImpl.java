package com.matchpointecv.matchpointecv.time;

import com.matchpointecv.matchpointecv.jogo.Jogo;
import com.matchpointecv.matchpointecv.jogo.JogoDTO;
import com.matchpointecv.matchpointecv.jogo.JogoRepository;
import com.matchpointecv.matchpointecv.jogo.JogoService;
import com.matchpointecv.matchpointecv.usuario.Usuario;
import com.matchpointecv.matchpointecv.usuario.UsuarioDTO;
import com.matchpointecv.matchpointecv.usuario.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TimeServiceImpl implements TimeService{

    @Autowired
    private TimeRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JogoService jogoService;
    @Autowired
    private UsuarioService usuarioService;

    public List<TimeDTO> getAll() {
        List<Time> times = repository.findAll();

        if (times.isEmpty()) {
            throw new NoSuchElementException("Nenhum time encontrado");
        }

        return times.stream()
                .map(time -> modelMapper.map(time, TimeDTO.class))
                .collect(Collectors.toList());
    }

    public TimeDTO save(TimeDTO timeDTO) {
        Time time = new Time();
        time.setId(timeDTO.getId());
        time.setNome(timeDTO.getNome());
        UsuarioDTO usuarioCapitaoDTO = usuarioService.getById(timeDTO.getId());
        Usuario usuarioCapitao = modelMapper.map(usuarioCapitaoDTO, Usuario.class);
        time.setCapitao(usuarioCapitao);

        List<Long> integrantesIds = timeDTO.getIntegrantes();
        List<UsuarioDTO> integrantesDTOs = usuarioService.getAllByIds(integrantesIds);
        List<Usuario> integrantes = integrantesDTOs.stream()
                .map(integranteDto -> modelMapper.map(integranteDto, Usuario.class))
                .toList();
        time.setIntegrantes(integrantes);

        List<Long> jogosIds = timeDTO.getJogos();
        List<JogoDTO> jogosDTOs = jogoService.getAllByIds(jogosIds);
        List<Jogo> jogos = jogosDTOs.stream()
                .map(jogoDTO -> modelMapper.map(jogoDTO, Jogo.class))
                .toList();
        time.setJogos(jogos);


        return modelMapper.map(repository.save(time), TimeDTO.class);
    }

}
