package com.apla77.gerenciamento.controller;

import com.apla77.gerenciamento.exception.ConsultaNotFoundException;
import com.apla77.gerenciamento.model.Endereco;
import com.apla77.gerenciamento.model.Pessoa;
import com.apla77.gerenciamento.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("")
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
        return new ResponseEntity<>(enderecoService.create(endereco), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Endereco> editarEndereco(@RequestBody Endereco endereco) {
        return new ResponseEntity<>(enderecoService.editarEndereco(endereco), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Endereco>> consultarEndereco(@PathVariable Long id) {
        List<Endereco> enderecos = enderecoService.consultarEnderecos(id);
        return ResponseEntity.ok().body(enderecos);
    }

    @ExceptionHandler(ConsultaNotFoundException.class)
    public ResponseEntity<String> handlePessoaNotFoundException(ConsultaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @GetMapping("")
    public ResponseEntity<List<Endereco>> consultaEnderecos() {
        return new ResponseEntity<>(enderecoService.consultarEnderecos(), HttpStatus.OK);
    }

    @PutMapping("/{idPessoa}/{idEndereco}")
    public ResponseEntity<String> indicarEnderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
        enderecoService.indicarEnderecoPrincipal(idPessoa, idEndereco);
        return ResponseEntity.ok().body("Endereço principal indicado com sucesso.");
    }
}
