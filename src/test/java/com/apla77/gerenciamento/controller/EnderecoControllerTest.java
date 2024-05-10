package com.apla77.gerenciamento.controller;

import com.apla77.gerenciamento.model.Endereco;
import com.apla77.gerenciamento.model.Pessoa;
import com.apla77.gerenciamento.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EnderecoControllerTest {

    @InjectMocks
    private EnderecoController enderecoController;

    @Mock
    private EnderecoService enderecoService;

    private Pessoa pessoa1;
    private Pessoa pessoa2;

    private Endereco endereco1;
    private Endereco endereco2;
    private Endereco endereco3;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        initValues();
    }

    @Test
    public void testCriarEndereco() {
        when(enderecoService.create(endereco1)).thenReturn(endereco1);
        ResponseEntity<Endereco> response = enderecoController.criarEndereco(endereco1);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(endereco1, response.getBody());
    }

    @Test
    public void testCriarEnderecoFalhaNaCriacao() {
        Endereco endereco = new Endereco();
        doThrow(new RuntimeException("Erro na criação")).when(enderecoService).create(any(Endereco.class));
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoController.criarEndereco(endereco));
        assertEquals("Erro na criação", exception.getMessage());
    }

    @Test
    public void testEditarEndereco() {
        when(enderecoService.editarEndereco(endereco2)).thenReturn(endereco2);
        ResponseEntity<Endereco> response = enderecoController.editarEndereco(endereco2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(endereco2, response.getBody());
    }

    @Test
    public void testEditarEnderecoFalhaNaEdicao() {
        Endereco endereco = new Endereco();
        doThrow(new RuntimeException("Erro na edição")).when(enderecoService).editarEndereco(any(Endereco.class));
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoController.editarEndereco(endereco));
        assertEquals("Erro na edição", exception.getMessage());
    }

    @Test
    public void testConsultarEndereco() {
        List<Endereco> enderecos = Arrays.asList(endereco3);
        when(enderecoService.consultarEnderecos(1L)).thenReturn(enderecos);
        ResponseEntity<List<Endereco>> response = enderecoController.consultarEndereco(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(enderecos, response.getBody());
    }

    @Test
    public void testConsultarEnderecoFalhaNaConsulta() {
        doThrow(new RuntimeException("Erro na consulta")).when(enderecoService).consultarEnderecos(1L);
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoController.consultarEndereco(1L));
        assertEquals("Erro na consulta", exception.getMessage());
    }

    @Test
    public void testConsultaEnderecos() {
        List<Endereco> enderecos = Arrays.asList(endereco1, endereco2, endereco3);
        when(enderecoService.consultarEnderecos()).thenReturn(enderecos);
        ResponseEntity<List<Endereco>> response = enderecoController.consultaEnderecos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(enderecos, response.getBody());
    }

    @Test
    public void testConsultaEnderecosFalhaNaConsulta() {
        doThrow(new RuntimeException("Erro na consulta")).when(enderecoService).consultarEnderecos();
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoController.consultaEnderecos());
        assertEquals("Erro na consulta", exception.getMessage());
    }

    @Test
    public void testIndicarEnderecoPrincipal() {
        Mockito.doNothing().when(enderecoService).indicarEnderecoPrincipal(1L, 1L);
        ResponseEntity<String> response = enderecoController.indicarEnderecoPrincipal(1L, 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Endereço principal indicado com sucesso.", response.getBody());
    }

    @Test
    public void testIndicarEnderecoPrincipalFalhaNaIndicacao() {
        doThrow(new RuntimeException("Erro na indicação")).when(enderecoService).indicarEnderecoPrincipal(1L, 1L);
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoController.indicarEnderecoPrincipal(1L, 1L));
        assertEquals("Erro na indicação", exception.getMessage());
    }

    public void initValues() {
        pessoa1 = new Pessoa(1L, "Jose Fernando", "09/02/1999", null);
        pessoa2 = new Pessoa(2L, "Astrogildo Pinto", "19/10/1949", null);
        endereco1 = new Endereco(1L, "Rua Jose", "59900-000", 23, "Natal", "RN", true, pessoa1);
        endereco2 = new Endereco(2L, "Rua da Ladeira", "59990-000", 33, "Tibau", "RN", true, pessoa1);
        endereco3 = new Endereco(3L, "Rua da Lagartixa", "59950-000", 33, "Itaim Paulista", "SP", true, pessoa2);

    }

}