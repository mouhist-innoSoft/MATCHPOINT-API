package com.matchpointecv.matchpointecv.participante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class ParticipanteServiceImpl implements ParticipanteService {

        @Autowired
        private ParticipanteRepository participanteRepository;

        private final ModelMapper modelMapper;

        public ParticipanteServiceImpl() {
            this.modelMapper = new ModelMapper();
        }

        @Override
        public ParticipanteDTO getById(Long id) {
            Participante participante = participanteRepository.findById(id).orElse(null);
            return convertToDTO(participante);
        }

        private ParticipanteDTO convertToDTO(Participante participante) {
                return null;
        }

        @Override
        public ParticipanteDTO save(ParticipanteDTO participanteDTO) {
            Participante participante = convertToEntity(participanteDTO);
            participante = participanteRepository.save(participante);
            return convertToDTO(participante);
        }

        private Participante convertToEntity(ParticipanteDTO participanteDTO) {
            Participante participante = new Participante();
            participante.setId(participante.getId());
            participante.setUsuarioId(participante.getUsuarioId());
            participante.setJogoId(participante.getJogoId());
            participante.setPosicaoPreferida(participante.getPosicaoPreferida());
            participante.setNivelHabilidade(participante.getNivelHabilidade());

            return modelMapper.map(participanteRepository.save(participante), Participante.class);

        }
    }




