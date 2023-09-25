package com.matchpointecv.matchpointecv.usuario;

import com.matchpointecv.matchpointecv.exception.RecordNotFoundException;
import com.matchpointecv.matchpointecv.jogo.Jogo;
import com.matchpointecv.matchpointecv.jogo.JogoDTO;
import com.matchpointecv.matchpointecv.jogo.JogoService;
import com.matchpointecv.matchpointecv.time.Time;
import com.matchpointecv.matchpointecv.time.TimeDTO;
import com.matchpointecv.matchpointecv.time.TimeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JogoService jogoService;
    @Autowired
    private TimeService timeService;

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
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setDataNascimento(usuarioDTO.getDataNascimento());

        List<Long> jogosIds = usuarioDTO.getJogosIds();
        if (!jogosIds.isEmpty()) {
            List<JogoDTO> jogosDTOs = jogoService.getAllByIds(jogosIds);
            List<Jogo> jogos = jogosDTOs.stream()
                    .map(jogoDTO -> modelMapper.map(jogoDTO, Jogo.class))
                    .toList();
            usuario.setJogos(jogos);
        }


        List<Long> timesIds = usuarioDTO.getTimesIds();
        if (!timesIds.isEmpty()) {
            List<TimeDTO> timesDtos = timeService.getAllByIds(timesIds);
            List<Time> times = timesDtos.stream()
                    .map(timeDTO -> modelMapper.map(timeDTO, Time.class))
                    .toList();
            usuario.setTimes(times);
        }


       return modelMapper.map(repository.save(usuario), UsuarioDTO.class);
    }

}
