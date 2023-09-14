package com.matchpointecv.matchpointecv.usuario;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("id")
    @Operation(summary = "Buscar usuário pelo id.")
    public Optional<Usuario> getById(Long id) {
        return service.getById(id);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os usuários.")
    public List<Usuario> getAll() {
        return service.getAll();
    }

    @PostMapping
    @Operation(summary = "Criar usuário.")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }

}
