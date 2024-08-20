package com.matchpointecv.matchpointecv.time;

import com.matchpointecv.matchpointecv.usuario.Usuario;
import com.matchpointecv.matchpointecv.usuario.dto.UsuarioDTO;
import com.matchpointecv.matchpointecv.usuario.UsuarioService;
import java.util.List;
import java.util.NoSuchElementException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService{

    @Autowired
    private TimeRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioService usuarioService;

    public List<TimeDTO> getAll() {
        List<Time> times = repository.findAll();

        if (times.isEmpty()) {
            throw new NoSuchElementException("Nenhum time encontrado");
        }

        return times.stream()
                .map(time -> modelMapper.map(time, TimeDTO.class))
                .toList();
    }

    public List<TimeDTO> getAllByIds(List<Long> ids) {
        List<Time> times = repository.findAllByIdIn(ids);

        if (times.isEmpty()) {
            throw new NoSuchElementException("Nenhum time encontrado");
        }

        return times.stream()
                .map(time -> modelMapper.map(time, TimeDTO.class))
                .toList();
    }

    public TimeDTO save(TimeDTO timeDTO) {
        Time time = new Time();
        time.setId(timeDTO.getId());
        time.setNome(timeDTO.getNome());
        UsuarioDTO usuarioCapitaoDTO = usuarioService.getById(timeDTO.getId());
        Usuario usuarioCapitao = modelMapper.map(usuarioCapitaoDTO, Usuario.class);
        time.setCapitao(usuarioCapitao);



        return modelMapper.map(repository.save(time), TimeDTO.class);
    }

}
