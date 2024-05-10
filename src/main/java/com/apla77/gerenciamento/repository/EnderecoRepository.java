package com.apla77.gerenciamento.repository;

import com.apla77.gerenciamento.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findAllByPessoaId(Long pessoaId);
}
