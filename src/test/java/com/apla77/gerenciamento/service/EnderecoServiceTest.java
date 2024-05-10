package com.apla77.gerenciamento.service;

import com.apla77.gerenciamento.model.Endereco;
import com.apla77.gerenciamento.model.Pessoa;
import com.apla77.gerenciamento.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    private Pessoa pessoa1;
    private Endereco endereco1;
    private Endereco endereco2;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        initValues();
    }

    @Test
    public void testCreate() {
        when(enderecoRepository.save(endereco1)).thenReturn(endereco1);
        assertEquals(endereco1, enderecoService.create(endereco1));
    }

    @Test
    public void testCreateFalhaNaCriacao() {
        Endereco endereco = new Endereco();
        doThrow(new RuntimeException("Erro na criação")).when(enderecoRepository).save(any(Endereco.class));
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoService.create(endereco));
        assertEquals("Erro na criação", exception.getMessage());
    }

    @Test
    public void testEditarEndereco() {
        when(enderecoRepository.save(endereco2)).thenReturn(endereco2);
        assertEquals(endereco2, enderecoService.editarEndereco(endereco2));
    }

    @Test
    public void testEditarEnderecoFalhaNaEdicao() {
        Endereco endereco = new Endereco(1L, null, null, 33, "Estado 1", "CEP 1", true, null);
        doThrow(new RuntimeException("Erro na edição")).when(enderecoRepository).save(any(Endereco.class));
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoService.editarEndereco(endereco));
        assertEquals("Erro na edição", exception.getMessage());
    }

    @Test
    public void testConsultarEnderecos() {
        List<Endereco> enderecos = Arrays.asList(endereco1, endereco2);
        when(enderecoRepository.findAll()).thenReturn(enderecos);
        assertEquals(enderecos, enderecoService.consultarEnderecos());
    }

    @Test
    public void testConsultarEnderecosFalhaNaConsulta() {
        doThrow(new RuntimeException("Erro na consulta")).when(enderecoRepository).findAll();
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoService.consultarEnderecos());
        assertEquals("Erro na consulta", exception.getMessage());
    }

    @Test
    public void testIndicarEnderecoPrincipal() {
        Long idPessoa = 1L;
        Long idEndereco = 1L;
        List<Endereco> enderecos = Arrays.asList(endereco1, endereco2);
        when(enderecoRepository.findAllByPessoaId(1L)).thenReturn(enderecos);
        enderecoService.indicarEnderecoPrincipal(idPessoa, idEndereco);
        verify(enderecoRepository, times(1)).findAllByPessoaId(idPessoa);
        verify(enderecoRepository, times(1)).save(endereco1);
        doReturn(null).when(enderecoRepository).save(any(Endereco.class));
        for (Endereco endereco : enderecos) {
            if (endereco.getId().equals(idEndereco)) {
                assert endereco.isPrincipal();
            } else {
                assert !endereco.isPrincipal();
            }
        }
    }

    public void initValues() {
        pessoa1 = new Pessoa(1L, "Jose Fernando", "09/02/1999", null);
        endereco1 = new Endereco(1L, "Rua Jose", "59900-000", 23, "Natal", "RN", true, pessoa1);
        endereco2 = new Endereco(2L, "Rua da Ladeira", "59990-000", 33, "Tibau", "RN", false, pessoa1);
    }
}