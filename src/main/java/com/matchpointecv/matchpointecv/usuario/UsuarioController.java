package com.matchpointecv.matchpointecv.usuario;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Buscar todos os usuários.")
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }
}
