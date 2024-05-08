package com.apla77.gerenciamento.controller;

import com.apla77.gerenciamento.model.Endereco;
import com.apla77.gerenciamento.model.Pessoa;
import com.apla77.gerenciamento.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> editarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.editarPessoa(id, pessoa);
        return ResponseEntity.ok().body(pessoaAtualizada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> consultarPessoa(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.consultarPessoa(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping("/{id}/enderecos")
    public ResponseEntity<Endereco> criarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        Endereco novoEndereco = pessoaService.criarEndereco(id, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @PutMapping("/{idPessoa}/enderecos/{idEndereco}")
    public ResponseEntity<Endereco> editarEndereco(@PathVariable Long idPessoa, @PathVariable Long idEndereco, @RequestBody Endereco endereco) {
        Endereco enderecoAtualizado = pessoaService.editarEndereco(idPessoa, idEndereco, endereco);
        return ResponseEntity.ok().body(enderecoAtualizado);
    }

    @GetMapping("/{id}/enderecos")
    public ResponseEntity<List<Endereco>> consultarEnderecos(@PathVariable Long id) {
        List<Endereco> enderecos = pessoaService.consultarEnderecos(id);
        return ResponseEntity.ok().body(enderecos);
    }

    @PutMapping("/{idPessoa}/enderecos/{idEndereco}/principal")
    public ResponseEntity<String> indicarEnderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
        pessoaService.indicarEnderecoPrincipal(idPessoa, idEndereco);
        return ResponseEntity.ok().body("Endereço principal indicado com sucesso.");
    }
}
