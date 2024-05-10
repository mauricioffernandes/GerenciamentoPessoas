package com.apla77.gerenciamento.controller;

import com.apla77.gerenciamento.exception.ConsultaNotFoundException;
import com.apla77.gerenciamento.model.Pessoa;
import com.apla77.gerenciamento.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Api(value = "PESSOA API REST")
@CrossOrigin(origins = "*")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("")
    @ApiOperation("Create a new pessoa")
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        return new ResponseEntity<>(pessoaService.create(pessoa), HttpStatus.CREATED);
    }

    @PutMapping("")
    @ApiOperation("Update a pessoa")
    public ResponseEntity<Pessoa> editarPessoa(@RequestBody Pessoa pessoa) {
        return new ResponseEntity<>(pessoaService.editarPessoa(pessoa), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a pessos by it's id")
    public ResponseEntity<Pessoa> consultarPessoa(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.consultarPessoa(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @GetMapping("")
    @ApiOperation("find a pessoas list")
    public ResponseEntity<List<Pessoa>> consultarPessoas() {
        return new ResponseEntity<>(pessoaService.consultarPessoas(), HttpStatus.OK);
    }

    @ExceptionHandler(ConsultaNotFoundException.class)
    public ResponseEntity<String> handlePessoaNotFoundException(ConsultaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
