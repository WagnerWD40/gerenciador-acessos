package com.acme.gerenciadoracessos.service;

import com.acme.gerenciadoracessos.exception.RecursoNaoEncontradoException;
import com.acme.gerenciadoracessos.model.Porta;
import com.acme.gerenciadoracessos.repository.PortaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PortaService {

    private final PortaRepository portaRepository;

    public PortaService(PortaRepository portaRepository) {
        this.portaRepository = portaRepository;
    }

    public Porta create(Porta porta) { return portaRepository.save(porta); }

    public List<Porta> getAll() { return (List<Porta>) portaRepository.findAll(); }

    public Porta getOne(UUID id) {
        Optional<Porta> porta = portaRepository.findById(id);
        return porta.orElseThrow(RecursoNaoEncontradoException::new);
    }

    public Porta update(Porta porta) {
        Porta foundPorta = getOne(porta.getId());
        foundPorta.merge(porta);
        return portaRepository.save(foundPorta);
    }


}
