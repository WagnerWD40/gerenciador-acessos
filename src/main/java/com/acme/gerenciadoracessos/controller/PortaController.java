package com.acme.gerenciadoracessos.controller;

import com.acme.gerenciadoracessos.model.Porta;
import com.acme.gerenciadoracessos.service.PortaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/portas")
public class PortaController {

    private final PortaService portaService;

    public PortaController(PortaService portaService) {
        this.portaService = portaService;
    }

    @GetMapping
    public List<Porta> getAll() {
        return portaService.getAll();
    }

    @GetMapping("/{id}")
    public Porta getOne(@PathVariable("id")UUID id) {
        return portaService.getOne(id);
    }

    @PostMapping
    public Porta create(@RequestBody Porta porta) {
        return portaService.create(porta);
    }

    @PutMapping
    public Porta update(@RequestBody Porta porta) {
        return portaService.update(porta);
    }
}
