package com.mindflow.api.psicologo;

import com.mindflow.api.psicologo.dto.PsicologoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/psicologos")
@AllArgsConstructor
public class PsicologoController {

    private final PsicologoService psicologoService;

    @PostMapping
    public ResponseEntity<PsicologoDTO> createPsicologo(@RequestBody PsicologoDTO psicologoDTO) {
        return new ResponseEntity<>(psicologoService.createPsicologo(psicologoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsicologoDTO> getPsicologoById(@PathVariable Long id) {
        return new ResponseEntity<>(psicologoService.getPsicologoById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PsicologoDTO>> getAllPsicologos() {
        return new ResponseEntity<>(psicologoService.getAllPsicologos(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PsicologoDTO> updatePsicologo(@PathVariable Long id, @RequestBody PsicologoDTO psicologoDTO) {
        return new ResponseEntity<>(psicologoService.updatePsicologo(id, psicologoDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePsicologo(@PathVariable Long id) {
        psicologoService.deletePsicologo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
