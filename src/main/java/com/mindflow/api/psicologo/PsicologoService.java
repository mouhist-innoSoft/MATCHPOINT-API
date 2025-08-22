package com.mindflow.api.psicologo;

import com.mindflow.api.psicologo.dto.PsicologoDTO;

import java.util.List;

public interface PsicologoService {

    PsicologoDTO createPsicologo(PsicologoDTO psicologoDTO);

    PsicologoDTO getPsicologoById(Long id);

    List<PsicologoDTO> getAllPsicologos();

    PsicologoDTO updatePsicologo(Long id, PsicologoDTO psicologoDTO);

    void deletePsicologo(Long id);
}
