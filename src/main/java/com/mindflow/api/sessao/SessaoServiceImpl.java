package com.mindflow.api.sessao;

import com.mindflow.api.exception.RecordNotFoundException;
import com.mindflow.api.sessao.dto.SessaoDTO;
import com.mindflow.api.consulta.Consulta;
import com.mindflow.api.consulta.ConsultaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SessaoServiceImpl implements SessaoService {

    private final SessaoRepository sessaoRepository;
    private final ConsultaRepository consultaRepository;

    @Override
    public SessaoDTO createSessao(SessaoDTO sessaoDTO) {
        Consulta consulta = consultaRepository.findById(sessaoDTO.consulta_id())
                .orElseThrow(() -> new RecordNotFoundException("Consulta", sessaoDTO.consulta_id()));

        Sessao sessao = Sessao.builder()
                .consulta(consulta)
                .resumoAtendimento(sessaoDTO.resumoAtendimento())
                .observacoes(sessaoDTO.observacoes())
                .dataRegistro(OffsetDateTime.now())
                .build();

        sessao = sessaoRepository.save(sessao);
        return toDTO(sessao);
    }

    @Override
    public SessaoDTO getSessaoById(Long id) {
        return sessaoRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RecordNotFoundException("Sessão", id));
    }

    @Override
    public List<SessaoDTO> getAllSessoes() {
        return sessaoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SessaoDTO> getSessoesByPaciente(Long pacienteId) {
        return sessaoRepository.findByConsulta_Paciente_Id(pacienteId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public SessaoDTO updateSessao(Long id, SessaoDTO sessaoDTO) {
        Sessao sessao = sessaoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Sessão", id));

        Consulta consulta = consultaRepository.findById(sessaoDTO.consulta_id())
                .orElseThrow(() -> new RecordNotFoundException("Consulta", sessaoDTO.consulta_id()));

        sessao.setConsulta(consulta);
        sessao.setResumoAtendimento(sessaoDTO.resumoAtendimento());
        sessao.setObservacoes(sessaoDTO.observacoes());

        sessao = sessaoRepository.save(sessao);
        return toDTO(sessao);
    }

    @Override
    public void deleteSessao(Long id) {
        sessaoRepository.delete(sessaoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Sessão", id)));
    }

    private SessaoDTO toDTO(Sessao sessao) {
        return SessaoDTO.builder()
                .id(sessao.getId())
                .consulta_id(sessao.getConsulta().getId())
                .resumoAtendimento(sessao.getResumoAtendimento())
                .observacoes(sessao.getObservacoes())
                .dataRegistro(sessao.getDataRegistro())
                .build();
    }
}
