package com.apla77.gerenciamento.service;

import com.apla77.gerenciamento.model.Endereco;
import com.apla77.gerenciamento.model.Pessoa;
import com.apla77.gerenciamento.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa criarPessoa(Pessoa pessoa) {
        // Implementação da lógica para criar uma pessoa
        return pessoaRepository.save(pessoa);
    }

    public Pessoa editarPessoa(Long id, Pessoa pessoa) {
        // Implementação da lógica para editar uma pessoa
        return pessoaRepository.save(pessoa);
    }

    public Pessoa consultarPessoa(Long id) {
        // Implementação da lógica para consultar uma pessoa
        return pessoaRepository.findById(id).orElse(null);
    }

    public Endereco criarEndereco(Long idPessoa, Endereco endereco) {
        // Implementação da lógica para criar um endereço para uma pessoa
        return endereco;
    }

    public Endereco editarEndereco(Long idPessoa, Long idEndereco, Endereco endereco) {
        // Implementação da lógica para editar um endereço de uma pessoa
        return endereco;
    }

    public List<Endereco> consultarEnderecos(Long id) {
        // Implementação da lógica para consultar os endereços de uma pessoa
        return null;
    }

    public void indicarEnderecoPrincipal(Long idPessoa, Long idEndereco) {
        // Implementação da lógica para indicar o endereço principal de uma pessoa
    }
}
