package com.matchpointecv.matchpointecv.jogo;

import com.matchpointecv.matchpointecv.exception.RecordNotFoundException;
import com.matchpointecv.matchpointecv.time.Time;
import com.matchpointecv.matchpointecv.time.TimeDTO;
import com.matchpointecv.matchpointecv.time.TimeService;
import com.matchpointecv.matchpointecv.usuario.Usuario;
import com.matchpointecv.matchpointecv.usuario.UsuarioDTO;
import com.matchpointecv.matchpointecv.usuario.UsuarioService;
import java.util.List;
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
    @Autowired
    private TimeService timeService;

    public Jogo getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Jogo", id));
    }

    public JogoDTO save(JogoDTO jogoDTO) {
        UsuarioDTO usuarioCriadorDTO = usuarioService.getById(jogoDTO.getCriadorId());
        Usuario usuarioCriador = modelMapper.map(usuarioCriadorDTO, Usuario.class);


            Jogo jogo = new Jogo();
            jogo.setData(jogoDTO.getData());
            jogo.setHora(jogoDTO.getHora());
            jogo.setLocal(jogoDTO.getLocal());
            jogo.setMaxParticipantes(jogoDTO.getMaxParticipantes());
            jogo.setCriadorId(usuarioCriador);

            List<Long> participantesIds = jogoDTO.getParticipantesIds();
            if (!participantesIds.isEmpty()) {
                List<UsuarioDTO> participantesDTOs = usuarioService.getAllByIds(participantesIds);
                List<Usuario> participantes = participantesDTOs.stream()
                        .map(participante -> modelMapper.map(participante, Usuario.class))
                        .toList();
                jogo.setParticipantes(participantes);
            }

            List<Long> timesIds = jogoDTO.getTimesIds();
            if (!timesIds.isEmpty()) {
                List<TimeDTO> timesDtos = timeService.getAllByIds(timesIds);
                List<Time> times = timesDtos.stream()
                        .map(timeDTO -> modelMapper.map(timeDTO, Time.class))
                        .toList();
                jogo.setTimes(times);
            }

           return modelMapper.map(repository.save(jogo), JogoDTO.class);

    }

    public List<JogoDTO> getAllByIds(List<Long> ids) {
        List<Jogo> jogos = repository.findAllByIdIn(ids);

        return jogos.stream()
                .map(jogo -> modelMapper.map(jogo, JogoDTO.class))
                .toList();
    }
}
