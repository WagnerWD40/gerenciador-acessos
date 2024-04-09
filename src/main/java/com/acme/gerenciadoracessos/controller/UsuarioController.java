package com.acme.gerenciadoracessos.controller;

import com.acme.gerenciadoracessos.model.Usuario;
import com.acme.gerenciadoracessos.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }

    @GetMapping("/{id}")
    public Usuario getOne(@PathVariable("id") UUID id) { return usuarioService.getOne(id); }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) { return usuarioService.create(usuario); }

    @PutMapping
    public Usuario update(@RequestBody Usuario usuario) {
        return usuarioService.update(usuario);
    }
}
