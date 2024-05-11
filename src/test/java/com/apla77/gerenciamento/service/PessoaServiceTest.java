package com.apla77.gerenciamento.service;

import com.apla77.gerenciamento.exception.ConsultaNotFoundException;
import com.apla77.gerenciamento.model.Pessoa;
import com.apla77.gerenciamento.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste de criação de pessoa com sucesso")
    public void testCreate() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa 1", "01/01/2000", null);
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        assertEquals(pessoa, pessoaService.create(pessoa));
    }

    @Test
    @DisplayName("Teste de criação de pessoa com id nulo e falha na criação")
    public void testCreateFalhaNaCriacao() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa 1", "01/01/2000", null);
        doThrow(new RuntimeException("Erro na criação")).when(pessoaRepository).save(any(Pessoa.class));
        Exception exception = assertThrows(RuntimeException.class, () -> pessoaService.create(pessoa));
        assertEquals("Erro na criação", exception.getMessage());
    }

    @Test
    @DisplayName("Teste de edição de pessoa com sucesso")
    public void testEditarPessoa() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa 1", "01/01/2000", null);
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        assertEquals(pessoa, pessoaService.editarPessoa(pessoa));
    }

    @Test
    @DisplayName("Teste de edição de pessoa com falha na edição")
    public void testEditarPessoaFalhaNaEdicao() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa 1", "01/01/2000", null);
        doThrow(new RuntimeException("Erro na edição")).when(pessoaRepository).save(any(Pessoa.class));
        Exception exception = assertThrows(RuntimeException.class, () -> pessoaService.editarPessoa(pessoa));
        assertEquals("Erro na edição", exception.getMessage());
    }

    @Test
    @DisplayName("Teste de edição de pessoa sem id com falha na edição")
    public void testEditarPessoaSemId() {
        Pessoa pessoa = new Pessoa();
        assertThrows(RuntimeException.class, () -> pessoaService.editarPessoa(pessoa));
    }

    @Test
    @DisplayName("Teste de consulta de pessoa com sucesso")
    public void testConsultarPessoa() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa", "01/01/2000", null);
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        assertEquals(pessoa, pessoaService.consultarPessoa(1L));
    }

    @Test
    @DisplayName("Teste de consulta de pessoa com falha na consulta")
    public void testConsultarPessoaFalhaNaConsulta() {
        doThrow(new RuntimeException("Erro na consulta")).when(pessoaRepository).findById(1L);
        Exception exception = assertThrows(RuntimeException.class, () -> pessoaService.consultarPessoa(1L));
        assertEquals("Erro na consulta", exception.getMessage());
    }

    @Test
    @DisplayName("Teste com falha de consulta de pessoa não existente")
    public void testConsultarPessoaNaoExistente() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ConsultaNotFoundException.class, () -> pessoaService.consultarPessoa(1L));
    }

    @Test
    @DisplayName("Teste de consulta de pessoas com sucesso")
    public void testConsultarPessoas() {
        Pessoa pessoa1 = new Pessoa(1L, "Pessoa 1", "01/01/2000", null);
        Pessoa pessoa2 = new Pessoa(2L, "Pessoa 2", "02/02/2000", null);
        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);
        when(pessoaRepository.findAll()).thenReturn(pessoas);
        assertEquals(pessoas, pessoaService.consultarPessoas());
    }

    @Test
    @DisplayName("Teste de consulta de pessoas com falha na consulta")
    public void testConsultarPessoasFalhaNaConsulta() {
        doThrow(new RuntimeException("Erro na consulta")).when(pessoaRepository).findAll();
        Exception exception = assertThrows(RuntimeException.class, () -> pessoaService.consultarPessoas());
        assertEquals("Erro na consulta", exception.getMessage());
    }
}