package com.matchpointecv.matchpointecv.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    @PostMapping()
    public ResponseEntity<String> fazerLogin(@RequestBody CredenciaisDTO credenciais) {

        return null;
    }
}
