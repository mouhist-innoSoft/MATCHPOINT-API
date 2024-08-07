package com.matchpointecv.matchpointecv.jogo;

import com.matchpointecv.matchpointecv.exception.RecordNotFoundException;
import com.matchpointecv.matchpointecv.jogo.jogoUsuario.ConfirmarcaoPresencaDTO;
import com.matchpointecv.matchpointecv.jogo.jogoUsuario.JogoUsuario;
import com.matchpointecv.matchpointecv.jogo.jogoUsuario.JogoUsuarioRepository;
import com.matchpointecv.matchpointecv.usuario.UsuarioService;
import com.matchpointecv.matchpointecv.usuario.UsuarioVisualizarDTO;
import jakarta.transaction.Transactional;
import java.util.List;
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
    @Autowired
    private JogoUsuarioRepository jogoUsuarioRepository;

    public Jogo getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Jogo", id));
    }

    @Override
    public JogoDTO save(JogoDTO jogoDTO) {

        Jogo jogo = new Jogo();
        jogo.setData(jogoDTO.getData());
        jogo.setHora(jogoDTO.getHora());
        jogo.setLocal(jogoDTO.getLocal());
        jogo.setMaxParticipantes(jogoDTO.getMaxParticipantes());
        jogo.setCriador(jogoDTO.getCriadorId());

        JogoDTO jogoSalvo = null;

        if (jogoDTO.getTipo().equals(TipoJogoEnum.PUB.getValue())) {
            jogo.setTipo(TipoJogoEnum.PUB.getValue());
            jogoSalvo = modelMapper.map(repository.save(jogo), JogoDTO.class);
        }

        if (jogoDTO.getTipo().equals(TipoJogoEnum.PRI.getValue())) {
            jogo.setTipo(TipoJogoEnum.PRI.getValue());

            jogoSalvo = modelMapper.map(repository.save(jogo), JogoDTO.class);

            if (!jogoDTO.getIdsParticipantes().isEmpty()) {
                for (Long participanteId : jogoDTO.getIdsParticipantes()) {
                    JogoUsuario jogoUsuario = new JogoUsuario();
                    jogoUsuario.setJogoId(jogoSalvo.getId());
                    jogoUsuario.setUsuarioId(participanteId);
                    jogoUsuario.setPermicao(true);
                    jogoUsuario.setPresencaConfirmada(false);

                    jogoUsuarioRepository.save(jogoUsuario);
                }
            }
        }
        return jogoSalvo;
    }

    @Override
    @Transactional
    public boolean confirmarPresenca(ConfirmarcaoPresencaDTO confirmarcaoPresencaDTO) {

        Jogo jogo = getById(confirmarcaoPresencaDTO.getJogoId());

        Boolean confirmado = null;
        JogoUsuario jogoUsuario = new JogoUsuario();
        if(jogo.getTipo().equals(TipoJogoEnum.PUB.getValue())) {
            jogoUsuario.setJogoId(confirmarcaoPresencaDTO.getJogoId());
            jogoUsuario.setUsuarioId(confirmarcaoPresencaDTO.getUsuarioId());
            confirmado = Optional.of(jogoUsuarioRepository.save(jogoUsuario)).isPresent();
        } else {
            Long jogoId = confirmarcaoPresencaDTO.getJogoId();
            Long usuarioId = confirmarcaoPresencaDTO.getUsuarioId();

            JogoUsuario jogoUsuarioEncontrado = jogoUsuarioRepository.findByJogoIdAndUsuarioId(jogoId, usuarioId);

            if(jogoUsuarioEncontrado != null) {
                jogoUsuarioRepository.updatePresencaConfirmada(true, jogoId, usuarioId);
                confirmado = true;
            } else {
                jogoUsuario.setJogoId(confirmarcaoPresencaDTO.getJogoId());
                jogoUsuario.setUsuarioId(confirmarcaoPresencaDTO.getUsuarioId());
                jogoUsuario.setPermicao(false);
                jogoUsuario.setPresencaConfirmada(true);
                confirmado = Optional.of(jogoUsuarioRepository.save(jogoUsuario)).isPresent();
            }

        }
        return confirmado;
    }

    @Override
    public JogoVisualizarDTO visualizar(Long id) {
        Optional<Jogo> jogo = repository.findById(id);
        List<Long> idsUsuarios = jogoUsuarioRepository.findAllUsuariosByJogoId(id);

        JogoVisualizarDTO dto = new JogoVisualizarDTO();

        if(jogo.isPresent()) {
            Jogo dadoJogo = jogo.get();
            dto.setId(dadoJogo.getId());
            dto.setData(dadoJogo.getData());
            dto.setHora(dadoJogo.getHora());
            dto.setLocal(dadoJogo.getLocal());
            dto.setMaxParticipantes(dadoJogo.getMaxParticipantes());
            dto.setCriadorId(dadoJogo.getCriador());
            dto.setTipo(dadoJogo.getTipo());

            if(!idsUsuarios.isEmpty()) {
                List<UsuarioVisualizarDTO> participantes = usuarioService.getAllByIds(idsUsuarios);
                if(participantes.size() == idsUsuarios.size()){
                    dto.setParticipantes(participantes);
                }
            }
            return dto;
        }

        throw new RecordNotFoundException("Jogo", id);
    }



    public List<JogoDTO> getAllByIds(List<Long> ids) {
        List<Jogo> jogos = repository.findAllByIdIn(ids);


        return jogos.stream()
                .map(jogo -> modelMapper.map(jogo, JogoDTO.class))
                .toList();
    }
}
