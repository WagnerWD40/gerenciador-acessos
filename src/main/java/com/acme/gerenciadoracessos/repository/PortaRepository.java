package com.acme.gerenciadoracessos.repository;

import com.acme.gerenciadoracessos.model.Porta;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PortaRepository extends CrudRepository<Porta, UUID> {
}
