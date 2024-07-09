package com.matchpointecv.matchpointecv.time;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TimeController {

    @Autowired
    private TimeService service;

    @GetMapping
    @Operation(summary = "Buscar todos os times")
    public List<TimeDTO> gelAll() {
        return service.getAll();
    }

    @PostMapping
    @Operation(summary = "Criar time.")
    public TimeDTO save(TimeDTO timeDTO) {
        return service.save(timeDTO);
    }
}
