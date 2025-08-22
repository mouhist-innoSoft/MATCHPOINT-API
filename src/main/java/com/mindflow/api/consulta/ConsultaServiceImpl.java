package com.mindflow.api.consulta;

import com.mindflow.api.exception.RecordNotFoundException;
import com.mindflow.api.consulta.dto.ConsultaDTO;
import com.mindflow.api.paciente.Paciente;
import com.mindflow.api.paciente.PacienteRepository;
import com.mindflow.api.psicologo.Psicologo;
import com.mindflow.api.psicologo.PsicologoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;

    @Override
    public ConsultaDTO createConsulta(ConsultaDTO consultaDTO) {
        Paciente paciente = pacienteRepository.findById(consultaDTO.paciente_id())
                .orElseThrow(() -> new RecordNotFoundException("Paciente", consultaDTO.paciente_id()));

        Psicologo psicologo = psicologoRepository.findById(consultaDTO.psicologo_id())
                .orElseThrow(() -> new RecordNotFoundException("Psicólogo", consultaDTO.psicologo_id()));

        Consulta consulta = Consulta.builder()
                .paciente(paciente)
                .psicologo(psicologo)
                .dataHora(consultaDTO.dataHora())
                .status(consultaDTO.status())
                .observacoes(consultaDTO.observacoes())
                .createdAt(OffsetDateTime.now())
                .build();

        consulta = consultaRepository.save(consulta);
        return toDTO(consulta);
    }

    @Override
    public ConsultaDTO getConsultaById(Long id) {
        return consultaRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RecordNotFoundException("Consulta", id));
    }

    @Override
    public List<ConsultaDTO> getAllConsultas() {
        return consultaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaDTO> getConsultasByPsicologoAndDateRange(Long psicologoId, OffsetDateTime start, OffsetDateTime end) {
        return consultaRepository.findByPsicologoIdAndDataHoraBetween(psicologoId, start, end).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaDTO> getConsultasByPsicologoAndDateRange(Long psicologoId, OffsetDateTime start, OffsetDateTime end) {
        return consultaRepository.findByPsicologoIdAndDataHoraBetween(psicologoId, start, end).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaDTO> getConsultasByPsicologoAndDateRange(Long psicologoId, OffsetDateTime start, OffsetDateTime end) {
        return consultaRepository.findByPsicologoIdAndDataHoraBetween(psicologoId, start, end).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ConsultaDTO updateConsulta(Long id, ConsultaDTO consultaDTO) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Consulta", id));

        Paciente paciente = pacienteRepository.findById(consultaDTO.paciente_id())
                .orElseThrow(() -> new RecordNotFoundException("Paciente", consultaDTO.paciente_id()));

        Psicologo psicologo = psicologoRepository.findById(consultaDTO.psicologo_id())
                .orElseThrow(() -> new RecordNotFoundException("Psicólogo", consultaDTO.psicologo_id()));

        consulta.setPaciente(paciente);
        consulta.setPsicologo(psicologo);
        consulta.setDataHora(consultaDTO.dataHora());
        consulta.setStatus(consultaDTO.status());
        consulta.setObservacoes(consultaDTO.observacoes());

        consulta = consultaRepository.save(consulta);
        return toDTO(consulta);
    }

    @Override
    public void deleteConsulta(Long id) {
        consultaRepository.delete(consultaRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Consulta", id)));
    }

    private ConsultaDTO toDTO(Consulta consulta) {
        return ConsultaDTO.builder()
                .id(consulta.getId())
                .paciente_id(consulta.getPaciente().getId())
                .psicologo_id(consulta.getPsicologo().getId())
                .dataHora(consulta.getDataHora())
                .status(consulta.getStatus())
                .observacoes(consulta.getObservacoes())
                .createdAt(consulta.getCreatedAt())
                .build();
    }
}
