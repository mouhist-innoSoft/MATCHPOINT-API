package com.matchpointecv.matchpointecv.participante;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService service;

    @PostMapping
    public ResponseEntity<ParticipanteDTO> save(@RequestBody ParticipanteDTO participanteDTO) {
        ParticipanteDTO savedParticipanteDTO = service.save(participanteDTO);
        return ResponseEntity.ok(savedParticipanteDTO);
    }
}
