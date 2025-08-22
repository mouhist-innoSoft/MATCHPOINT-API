package com.mindflow.api.sessao;

import com.mindflow.api.sessao.dto.SessaoDTO;

import java.util.List;

public interface SessaoService {

    SessaoDTO createSessao(SessaoDTO sessaoDTO);

    SessaoDTO getSessaoById(Long id);

    List<SessaoDTO> getAllSessoes();

    List<SessaoDTO> getSessoesByPaciente(Long pacienteId);

    SessaoDTO updateSessao(Long id, SessaoDTO sessaoDTO);

    void deleteSessao(Long id);
}
