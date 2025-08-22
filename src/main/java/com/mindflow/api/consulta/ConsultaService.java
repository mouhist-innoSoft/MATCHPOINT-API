package com.mindflow.api.consulta;

import com.mindflow.api.consulta.dto.ConsultaDTO;

import java.util.List;

public interface ConsultaService {

    ConsultaDTO createConsulta(ConsultaDTO consultaDTO);

    ConsultaDTO getConsultaById(Long id);

    List<ConsultaDTO> getAllConsultas();

    List<ConsultaDTO> getConsultasByPsicologoAndDateRange(Long psicologoId, OffsetDateTime start, OffsetDateTime end);

    ConsultaDTO updateConsulta(Long id, ConsultaDTO consultaDTO);

    void deleteConsulta(Long id);
}
