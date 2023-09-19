package com.matchpointecv.matchpointecv.convite;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/convites")
public class ConviteController {

    @Autowired
    private ConviteService service;

    @GetMapping("id")
    @Operation(summary = "Buscar Convite pelo id.")
    public Convite getById(Long id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Criar convite")
    public ConviteDTO save(@RequestBody ConviteDTO conviteDTO) {
        return service.save(conviteDTO);
    }
}



