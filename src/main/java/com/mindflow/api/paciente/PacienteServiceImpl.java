package com.mindflow.api.paciente;

import com.mindflow.api.exception.RecordNotFoundException;
import com.mindflow.api.paciente.dto.PacienteDTO;
import com.mindflow.api.psicologo.Psicologo;
import com.mindflow.api.psicologo.PsicologoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;

    @Override
    public PacienteDTO createPaciente(PacienteDTO pacienteDTO) {
        Psicologo psicologo = psicologoRepository.findById(pacienteDTO.psicologo_id())
                .orElseThrow(() -> new RecordNotFoundException("Psicólogo", pacienteDTO.psicologo_id()));

        Paciente paciente = Paciente.builder()
                .nome(pacienteDTO.nome())
                .contato(pacienteDTO.contato())
                .dataNascimento(LocalDate.parse(pacienteDTO.dataNascimento()))
                .psicologo(psicologo)
                .observacoes(pacienteDTO.observacoes())
                .createdAt(OffsetDateTime.now())
                .build();

        paciente = pacienteRepository.save(paciente);
        return toDTO(paciente);
    }

    @Override
    public PacienteDTO getPacienteById(Long id) {
        return pacienteRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RecordNotFoundException("Paciente", id));
    }

    @Override
    public List<PacienteDTO> getAllPacientes() {
        return pacienteRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PacienteDTO updatePaciente(Long id, PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Paciente", id));

        Psicologo psicologo = psicologoRepository.findById(pacienteDTO.psicologo_id())
                .orElseThrow(() -> new RecordNotFoundException("Psicólogo", pacienteDTO.psicologo_id()));

        paciente.setNome(pacienteDTO.nome());
        paciente.setContato(pacienteDTO.contato());
        paciente.setDataNascimento(LocalDate.parse(pacienteDTO.dataNascimento()));
        paciente.setPsicologo(psicologo);
        paciente.setObservacoes(pacienteDTO.observacoes());

        paciente = pacienteRepository.save(paciente);
        return toDTO(paciente);
    }

    @Override
    public void deletePaciente(Long id) {
        pacienteRepository.delete(pacienteRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Paciente", id)));
    }

    private PacienteDTO toDTO(Paciente paciente) {
        return PacienteDTO.builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .contato(paciente.getContato())
                .dataNascimento(paciente.getDataNascimento().toString())
                .psicologo_id(paciente.getPsicologo().getId())
                .createdAt(paciente.getCreatedAt())
                .observacoes(paciente.getObservacoes())
                .build();
    }
}
