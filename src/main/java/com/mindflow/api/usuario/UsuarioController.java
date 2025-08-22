package com.mindflow.api.usuario;

import com.mindflow.api.usuario.dto.CriarUsuarioDTO;
import com.mindflow.api.usuario.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário pelo id.")
    public UsuarioDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os usuários.")
    public List<Usuario> getAll() {
        return service.getAll();
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Criar usuário.")
    public ResponseEntity<Boolean> save(@RequestBody CriarUsuarioDTO criarUsuarioDTO) {
        boolean usuarioInsert = service.save(criarUsuarioDTO);
        return Optional.ofNullable(usuarioInsert).isPresent() ? ResponseEntity.ok(usuarioInsert)
                : ResponseEntity.noContent().build();
    }

}
