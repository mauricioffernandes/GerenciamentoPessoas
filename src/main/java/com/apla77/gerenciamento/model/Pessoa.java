package com.apla77.gerenciamento.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private String dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Endereco> enderecos = null;
}
