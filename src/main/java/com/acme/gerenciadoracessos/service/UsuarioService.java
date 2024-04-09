package com.acme.gerenciadoracessos.service;

import com.acme.gerenciadoracessos.exception.RecursoNaoEncontradoException;
import com.acme.gerenciadoracessos.exception.UsuarioJaPossuiAcessoException;
import com.acme.gerenciadoracessos.model.Porta;
import com.acme.gerenciadoracessos.model.Usuario;
import com.acme.gerenciadoracessos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PortaService portaService;

    public UsuarioService(UsuarioRepository usuarioRepository, PortaService portaService) {
        this.usuarioRepository = usuarioRepository;
        this.portaService = portaService;
    }

    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public  List<Usuario> getAll() { return (List<Usuario>) usuarioRepository.findAll(); }

    public Usuario getOne(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(RecursoNaoEncontradoException::new);
    }

    public Usuario update(Usuario usuario) {
        Usuario foundUsuario = getOne(usuario.getId());
        foundUsuario.merge(usuario);
        return  usuarioRepository.save(foundUsuario);
    }

    public void adicionarAcesso(UUID usuarioId, UUID portaId) {
        Usuario foundUsuario = getOne(usuarioId);
        Porta foundPorta = portaService.getOne(portaId);

        var acessos = foundUsuario.getAcessos();

        if (!acessos.isEmpty()) {
            acessos
                .stream()
                .filter(a -> a.getId() == portaId)
                .findAny()
                .orElseThrow(UsuarioJaPossuiAcessoException::new);
        }

        acessos.add(foundPorta);

        foundUsuario.setAcessos(acessos);
        usuarioRepository.save(foundUsuario);
    }

    public void removerAcesso(UUID usuarioId, UUID portaId) {
        Usuario foundUsuario = getOne(usuarioId);
        Porta foundPorta = portaService.getOne(portaId);

        var acessos = foundUsuario
                        .getAcessos()
                        .stream()
                        .filter(p -> p.getId() != foundPorta.getId())
                        .toList();

        foundUsuario.setAcessos(acessos);
        usuarioRepository.save(foundUsuario);
    }

    public boolean checkAcesso(UUID usuarioId, UUID portaId) {
        Usuario foundUsuario = getOne(usuarioId);
        Porta foundPorta = portaService.getOne(portaId);

        return foundUsuario.getAcessos().contains(foundPorta);
    }

}
