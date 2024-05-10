package com.apla77.gerenciamento.service;

import com.apla77.gerenciamento.exception.ConsultaNotFoundException;
import com.apla77.gerenciamento.model.Endereco;
import com.apla77.gerenciamento.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco create(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco editarEndereco(Endereco endereco) {
        if(endereco.getId() == null) {
            throw new RuntimeException("Id do endereco não informado");
        }
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> consultarEnderecos(Long id) {
        return (List<Endereco>) enderecoRepository.findById(id).orElseThrow(() -> new ConsultaNotFoundException(
                "Endereco não encontrada, ID: " + id)
        );
    }

    public List<Endereco> consultarEnderecos() {
        return enderecoRepository.findAll();
    }

    public void indicarEnderecoPrincipal(Long idPessoa, Long idEndereco) {
        List<Endereco> enderecos = enderecoRepository.findAllByPessoaId(idPessoa);
        enderecos.forEach(endereco -> {
            if (endereco.getId().equals(idEndereco)) {
                endereco.setPrincipal(true);
            } else {
                endereco.setPrincipal(false);
            }
            enderecoRepository.save(endereco);
        });
    }
}
