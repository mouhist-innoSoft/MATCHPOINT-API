package com.mindflow.api.auth;

import com.mindflow.api.auth.dto.CredenciaisDTO;
import com.mindflow.api.auth.dto.TokenDTO;
import com.mindflow.api.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> loginUsuario(@RequestBody CredenciaisDTO credenciaisDTO) {
        TokenDTO token = usuarioService.autenticarUsuario(credenciaisDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
