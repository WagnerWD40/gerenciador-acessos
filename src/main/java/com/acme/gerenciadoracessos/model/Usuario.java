package com.acme.gerenciadoracessos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String cpf;

    @OneToMany
    private List<Porta> acessos;

    public Usuario merge(Usuario other) {
        this.setNome(other.getNome() == null ? this.getNome() : other.getNome());
        this.setCpf(other.getCpf() == null ? this.getCpf() : other.getCpf());
        return this;
    }
}
