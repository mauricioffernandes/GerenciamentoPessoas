package com.apla77.gerenciamento.controller;

import com.apla77.gerenciamento.model.Pessoa;
import com.apla77.gerenciamento.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarPessoa() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa 1", "01/01/2000", null);
        when(pessoaService.create(pessoa)).thenReturn(pessoa);
        ResponseEntity<Pessoa> response = pessoaController.criarPessoa(pessoa);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pessoa, response.getBody());
    }

    @Test
    public void testCriarPessoaFalhaNaGravacao() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa 1", "01/01/2000", null);
        doThrow(new RuntimeException("Erro de gravação")).when(pessoaService).create(any(Pessoa.class));
        Exception exception = assertThrows(RuntimeException.class, () -> pessoaController.criarPessoa(pessoa));
        assertEquals("Erro de gravação", exception.getMessage());
    }

    @Test
    public void testEditarPessoa() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa 1", "01/01/2000", null);
        when(pessoaService.editarPessoa(pessoa)).thenReturn(pessoa);
        ResponseEntity<Pessoa> response = pessoaController.editarPessoa(pessoa);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoa, response.getBody());
    }

    @Test
    public void testEditarPessoaFalhaNaEdicao() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa 1", "01/01/2000", null);
        doThrow(new RuntimeException("Erro na edição")).when(pessoaService).editarPessoa(any(Pessoa.class));
        Exception exception = assertThrows(RuntimeException.class, () -> pessoaController.editarPessoa(pessoa));
        assertEquals("Erro na edição", exception.getMessage());
    }

    @Test
    public void testConsultarPessoa() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa 1", "01/01/2000", null);
        when(pessoaService.consultarPessoa(1L)).thenReturn(pessoa);
        ResponseEntity<Pessoa> response = pessoaController.consultarPessoa(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoa, response.getBody());
    }

    @Test
    public void testConsultarPessoaFalhaNaConsulta() {
        doThrow(new RuntimeException("Erro na consulta")).when(pessoaService).consultarPessoa(1L);
        Exception exception = assertThrows(RuntimeException.class, () -> pessoaController.consultarPessoa(1L));
        assertEquals("Erro na consulta", exception.getMessage());
    }

    @Test
    public void testConsultarPessoas() {
        Pessoa pessoa1 = new Pessoa(1L, "Pessoa 1", "01/01/2000", null);
        Pessoa pessoa2 = new Pessoa(2L, "Pessoa 2", "02/02/2000", null);
        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);
        when(pessoaService.consultarPessoas()).thenReturn(pessoas);
        ResponseEntity<List<Pessoa>> response = pessoaController.consultarPessoas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoas, response.getBody());
    }

}