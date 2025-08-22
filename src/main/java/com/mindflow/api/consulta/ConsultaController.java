package com.mindflow.api.consulta;

import com.mindflow.api.consulta.dto.ConsultaDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@AllArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaDTO> createConsulta(@RequestBody ConsultaDTO consultaDTO) {
        return new ResponseEntity<>(consultaService.createConsulta(consultaDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable Long id) {
        return new ResponseEntity<>(consultaService.getConsultaById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> getAllConsultas() {
        return new ResponseEntity<>(consultaService.getAllConsultas(), HttpStatus.OK);
    }

    @GetMapping("/psicologo/{psicologoId}")
    public ResponseEntity<List<ConsultaDTO>> getConsultasByPsicologoAndDateRange(@PathVariable Long psicologoId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime start, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime end) {
        return new ResponseEntity<>(consultaService.getConsultasByPsicologoAndDateRange(psicologoId, start, end), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> updateConsulta(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) {
        return new ResponseEntity<>(consultaService.updateConsulta(id, consultaDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        consultaService.deleteConsulta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
