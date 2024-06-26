package com.acme.gerenciadoracessos.repository;

import com.acme.gerenciadoracessos.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UsuarioRepository extends CrudRepository<Usuario, UUID> {
}
