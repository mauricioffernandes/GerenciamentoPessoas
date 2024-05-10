package com.apla77.gerenciamento.service;

import com.apla77.gerenciamento.exception.ConsultaNotFoundException;
import com.apla77.gerenciamento.model.Pessoa;
import com.apla77.gerenciamento.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {


    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa create(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa editarPessoa(Pessoa pessoa) {
        if(pessoa.getId() == null) {
            throw new RuntimeException("Id da pessoa não informado");
        }
        return pessoaRepository.save(pessoa);
    }

    public Pessoa consultarPessoa(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new ConsultaNotFoundException(
                "Pessoa não encontrada, ID: " + id)
        );
    }

    public List<Pessoa> consultarPessoas() {
        return pessoaRepository.findAll();
    }
}
