package com.matchpointecv.matchpointecv.time;

import com.matchpointecv.matchpointecv.jogo.Jogo;
import com.matchpointecv.matchpointecv.jogo.JogoDTO;
import com.matchpointecv.matchpointecv.jogo.JogoRepository;
import com.matchpointecv.matchpointecv.jogo.JogoService;
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
        time.setCapitao(timeDTO.getCapitao());


        time.setIntegrantes();

        List<Long> jogosIds = timeDTO.getJogos();
        List<JogoDTO> jogosDTOS = jogoService.getAllByIds(jogosIds);
        List<Jogo> jogos = jogosDTOS.stream()
                .map(jogoDTO -> modelMapper.map(jogoDTO, Jogo.class))
                .toList();
        time.setJogos(jogos);


        return modelMapper.map(repository.save(time), TimeDTO.class);
    }

}
