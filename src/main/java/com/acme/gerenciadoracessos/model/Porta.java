package com.acme.gerenciadoracessos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "portas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Porta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    public Porta merge(Porta other) {
        this.setNome(other.nome);

        return this;
    }
}
