package com.matchpointecv.matchpointecv.jogo;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/jogo")
public class JogoController {

    @Autowired
    private JogoService service;

    @GetMapping("id")
    @Operation(summary = "Buscar jogo pelo id.")
    public Optional<Jogo> getById(Long id) {
       return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Criar jogo")
    public ResponseEntity<JogoDTO> save(@RequestBody JogoDTO jogoDTO) {
        return ResponseEntity.ok().body(service.save(jogoDTO));
    }
}
