package com.apla77.gerenciamento.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String cep;
    private int numero;
    private String cidade;
    private String estado;
    private boolean principal = true;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    @JsonBackReference
    private Pessoa pessoa;
}
