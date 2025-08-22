package com.mindflow.api.paciente;

import com.mindflow.api.paciente.dto.PacienteDTO;

import java.util.List;

public interface PacienteService {

    PacienteDTO createPaciente(PacienteDTO pacienteDTO);

    PacienteDTO getPacienteById(Long id);

    List<PacienteDTO> getAllPacientes();

    PacienteDTO updatePaciente(Long id, PacienteDTO pacienteDTO);

    void deletePaciente(Long id);
}
