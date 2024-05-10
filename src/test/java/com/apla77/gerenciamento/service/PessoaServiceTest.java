package com.apla77.gerenciamento.service;

import com.apla77.gerenciamento.exception.ConsultaNotFoundException;
import com.apla77.gerenciamento.model.Pessoa;
import com.apla77.gerenciamento.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    public void testCreate() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa 1", "01/01/2000", null);
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        assertEquals(pessoa, pessoaService.create(pessoa));
    }

    @Test
    public void testEditarPessoa() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa 1", "01/01/2000", null);
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        assertEquals(pessoa, pessoaService.editarPessoa(pessoa));
    }

    @Test
    public void testEditarPessoaSemId() {
        Pessoa pessoa = new Pessoa();
        assertThrows(RuntimeException.class, () -> pessoaService.editarPessoa(pessoa));
    }

    @Test
    public void testConsultarPessoa() {
        Pessoa pessoa = new Pessoa(1L,"Pessoa", "01/01/2000", null);
        pessoa.setId(1L);
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        assertEquals(pessoa, pessoaService.consultarPessoa(1L));
    }

    @Test
    public void testConsultarPessoaNaoExistente() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ConsultaNotFoundException.class, () -> pessoaService.consultarPessoa(1L));
    }

    @Test
    public void testConsultarPessoas() {
        Pessoa pessoa1 = new Pessoa(1L, "Pessoa 1", "01/01/2000", null);
        Pessoa pessoa2 = new Pessoa(2L, "Pessoa 2", "02/02/2000", null);
        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);
        when(pessoaRepository.findAll()).thenReturn(pessoas);
        assertEquals(pessoas, pessoaService.consultarPessoas());
    }

}