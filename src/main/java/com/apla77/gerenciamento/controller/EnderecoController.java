package com.apla77.gerenciamento.controller;

import com.apla77.gerenciamento.exception.ConsultaNotFoundException;
import com.apla77.gerenciamento.model.Endereco;
import com.apla77.gerenciamento.service.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
@Api(value = "ENDERECO API REST")
@CrossOrigin(origins = "*")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("")
    @ApiOperation("Create a new endereço")
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
        return new ResponseEntity<>(enderecoService.create(endereco), HttpStatus.CREATED);
    }

    @PutMapping("")
    @ApiOperation("Update a endereço")
    public ResponseEntity<Endereco> editarEndereco(@RequestBody Endereco endereco) {
        return new ResponseEntity<>(enderecoService.editarEndereco(endereco), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a endereço by it's id")
    public ResponseEntity<List<Endereco>> consultarEndereco(@PathVariable Long id) {
        List<Endereco> enderecos = enderecoService.consultarEnderecos(id);
        return ResponseEntity.ok().body(enderecos);
    }

    @GetMapping("")
    @ApiOperation("find a enderecos list")
    public ResponseEntity<List<Endereco>> consultaEnderecos() {
        return new ResponseEntity<>(enderecoService.consultarEnderecos(), HttpStatus.OK);
    }

    @PutMapping("/{idPessoa}/{idEndereco}")
    @ApiOperation("Indicate a endereço as principal")
    public ResponseEntity<String> indicarEnderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
        enderecoService.indicarEnderecoPrincipal(idPessoa, idEndereco);
        return ResponseEntity.ok().body("Endereço principal indicado com sucesso.");
    }

    @ExceptionHandler(ConsultaNotFoundException.class)
    public ResponseEntity<String> handlePessoaNotFoundException(ConsultaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
