package com.apla77.gerenciamento.service;

import com.apla77.gerenciamento.exception.ConsultaNotFoundException;
import com.apla77.gerenciamento.model.Endereco;
import com.apla77.gerenciamento.model.Pessoa;
import com.apla77.gerenciamento.repository.EnderecoRepository;
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
    @DisplayName("Teste de criação de endereço")
    public void testCreate() {
        when(enderecoRepository.save(endereco1)).thenReturn(endereco1);
        assertEquals(endereco1, enderecoService.create(endereco1));
    }

    @Test
    @DisplayName("Teste de criação de endereço com id nulo")
    public void testCreateFalhaNaCriacao() {
        Endereco endereco = new Endereco();
        doThrow(new RuntimeException("Erro na criação")).when(enderecoRepository).save(any(Endereco.class));
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoService.create(endereco));
        assertEquals("Erro na criação", exception.getMessage());
    }

    @Test
    @DisplayName("Teste de edição de endereço")
    public void testEditarEndereco() {
        when(enderecoRepository.save(endereco2)).thenReturn(endereco2);
        assertEquals(endereco2, enderecoService.editarEndereco(endereco2));
    }

    @Test
    @DisplayName("Teste de edição de endereço com id nulo")
    public void testEditarEndereco_IdNulo() {
        Endereco endereco = new Endereco();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);
        EnderecoService enderecoService = new EnderecoService(enderecoRepository);
        assertThrows(RuntimeException.class, () -> {
            enderecoService.editarEndereco(endereco);
        });
        verify(enderecoRepository, never()).save(any(Endereco.class));
    }

    @Test
    @DisplayName("Teste de edição de endereço com falha na edição")
    public void testEditarEnderecoFalhaNaEdicao() {
        Endereco endereco = new Endereco(1L, null, null, 33, "Estado 1", "CEP 1", true, null);
        doThrow(new RuntimeException("Erro na edição")).when(enderecoRepository).save(any(Endereco.class));
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoService.editarEndereco(endereco));
        assertEquals("Erro na edição", exception.getMessage());
    }

    @Test
    @DisplayName("Teste de consulta de endereço com sucesso")
    public void testConsultarEndereco() {
        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco1));
        assertEquals(endereco1, enderecoService.consultarEndereco(1L));
    }

    @Test
    @DisplayName("Teste de consulta de endereço com falha na consulta")
    public void testConsultarEnderecoComFalhaNaConsulta() {
        doThrow(new RuntimeException("Erro na consulta")).when(enderecoRepository).findById(1L);
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoService.consultarEndereco(1L));
        assertEquals("Erro na consulta", exception.getMessage());
    }


    @Test
    @DisplayName("Teste de consulta de endereços com sucesso")
    public void testConsultarEnderecos() {
        List<Endereco> enderecos = Arrays.asList(endereco1, endereco2);
        when(enderecoRepository.findAll()).thenReturn(enderecos);
        assertEquals(enderecos, enderecoService.consultarEnderecos());
    }

    @Test
    @DisplayName("Teste de consulta de endereço com falha na consulta")
    public void testConsultarEnderecosFalhaNaConsulta() {
        doThrow(new RuntimeException("Erro na consulta")).when(enderecoRepository).findAll();
        Exception exception = assertThrows(RuntimeException.class, () -> enderecoService.consultarEnderecos());
        assertEquals("Erro na consulta", exception.getMessage());
    }

    @Test
    @DisplayName("Teste de consulta de endereço com endereço encontrado")
    public void testConsultarEnderecos_EnderecoEncontrados() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.empty());
        EnderecoService enderecoService = new EnderecoService(enderecoRepository);

        assertThrows(ConsultaNotFoundException.class, () -> {
            enderecoService.consultarEndereco(1L);
        });
    }

    @Test
    @DisplayName("Teste de indicação de endereço principal com sucesso")
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

    @DisplayName("Método para inicializar valores")
    public void initValues() {
        pessoa1 = new Pessoa(1L, "Jose Fernando", "09/02/1999", null);
        endereco1 = new Endereco(1L, "Rua Jose", "59900-000", 23, "Natal", "RN", true, pessoa1);
        endereco2 = new Endereco(2L, "Rua da Ladeira", "59990-000", 33, "Tibau", "RN", false, pessoa1);
    }
}