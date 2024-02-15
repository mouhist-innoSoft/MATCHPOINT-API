package com.matchpointecv.matchpointecv.participante;


public interface ParticipanteService {

    ParticipanteDTO getById(Long id);

    ParticipanteDTO save(ParticipanteDTO participanteDTO);
}
