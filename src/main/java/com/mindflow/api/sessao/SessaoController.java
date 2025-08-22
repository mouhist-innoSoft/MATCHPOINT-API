package com.mindflow.api.sessao;

import com.mindflow.api.sessao.dto.SessaoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessoes")
@AllArgsConstructor
public class SessaoController {

    private final SessaoService sessaoService;

    @PostMapping
    public ResponseEntity<SessaoDTO> createSessao(@RequestBody SessaoDTO sessaoDTO) {
        return new ResponseEntity<>(sessaoService.createSessao(sessaoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessaoDTO> getSessaoById(@PathVariable Long id) {
        return new ResponseEntity<>(sessaoService.getSessaoById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SessaoDTO>> getAllSessoes() {
        return new ResponseEntity<>(sessaoService.getAllSessoes(), HttpStatus.OK);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<SessaoDTO>> getSessoesByPaciente(@PathVariable Long pacienteId) {
        return new ResponseEntity<>(sessaoService.getSessoesByPaciente(pacienteId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessaoDTO> updateSessao(@PathVariable Long id, @RequestBody SessaoDTO sessaoDTO) {
        return new ResponseEntity<>(sessaoService.updateSessao(id, sessaoDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessao(@PathVariable Long id) {
        sessaoService.deleteSessao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
