package com.mindflow.api.paciente;

import com.mindflow.api.paciente.dto.PacienteDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@AllArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        return new ResponseEntity<>(pacienteService.createPaciente(pacienteDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
        return new ResponseEntity<>(pacienteService.getPacienteById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
        return new ResponseEntity<>(pacienteService.getAllPacientes(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        return new ResponseEntity<>(pacienteService.updatePaciente(id, pacienteDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.deletePaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
