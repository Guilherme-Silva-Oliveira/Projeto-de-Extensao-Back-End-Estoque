package school.sptech.sistema_estoque.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Motivo;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.port.MotivoPort;
import school.sptech.sistema_estoque.port.ProfessorPort;
import school.sptech.sistema_estoque.port.SolicitacaoPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class SolicitacaoServiceTest {

    @InjectMocks
    SolicitacaoService service;

    @Mock
    ProfessorPort professorPort;
    @Mock
    SolicitacaoPort solicitacaoPort;
    @Mock
    MotivoPort motivoPort;



    @Nested
    @DisplayName("Testes do metodo cadastrarSolicitacao")
    class MetodoCadastrar {

        @Test
        @DisplayName("Deve cadastrar corretamente")
        void deveCadastrarCorretamente() {

            Integer idProfessor = 12;
            Integer idMotivo = 2;
            String descricao = "Descricao";
            LocalDateTime dataSolicitacao = LocalDateTime.MIN;

            Professor professor = new Professor();
            professor.setId(idProfessor);
            professor.setEmail("email@email.com");

            Motivo motivo = new Motivo();

            Solicitacao solicitacaoSalva = new Solicitacao();
            solicitacaoSalva.setDescricao(descricao);

            SolicitacaoRequest request =
                    new SolicitacaoRequest(idProfessor, idMotivo, descricao, dataSolicitacao);

            Mockito.when(professorPort.findById(idProfessor)).thenReturn(Optional.of(professor));
            Mockito.when(motivoPort.findById(idMotivo)).thenReturn(Optional.of(motivo));
            Mockito.when(solicitacaoPort.save(any(Solicitacao.class))).thenReturn(solicitacaoSalva);

            Solicitacao resultado = service.cadastrarSolicitacao(request);

            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(descricao, resultado.getDescricao());
            Mockito.verify(solicitacaoPort, Mockito.times(1)).save(any(Solicitacao.class));
        }

        @Test
        @DisplayName("Deve lançar Exception quando request for nulo")
        void deveLancarExceptionRequestNull() {
            Assertions.assertThrows(EntidadeInvalidException.class, () ->
                    service.cadastrarSolicitacao(null));
        }

        @Test
        @DisplayName("Deve lançar Exception quando professor não for encontrado")
        void deveLancarExceptionProfessorNaoEncontrado() {

            SolicitacaoRequest request =
                    new SolicitacaoRequest(99, 1, "Descricao", LocalDateTime.MIN);

            Mockito.when(professorPort.findById(99)).thenReturn(Optional.empty());

            Assertions.assertThrows(EntidadeNaoExisteException.class, () ->
                    service.cadastrarSolicitacao(request));

            Mockito.verify(solicitacaoPort, Mockito.never()).save(any());
        }

        @Test
        @DisplayName("Deve lançar Exception quando motivo não for encontrado")
        void deveLancarExceptionMotivoNaoEncontrado() {

            Integer idProfessor = 12;
            Professor professor = new Professor();
            professor.setId(idProfessor);

            SolicitacaoRequest request =
                    new SolicitacaoRequest(idProfessor, 99, "Descricao", LocalDateTime.MIN);

            Mockito.when(professorPort.findById(idProfessor)).thenReturn(Optional.of(professor));
            Mockito.when(motivoPort.findById(99)).thenReturn(Optional.empty());

            Assertions.assertThrows(EntidadeNaoExisteException.class, () ->
                    service.cadastrarSolicitacao(request));

            Mockito.verify(solicitacaoPort, Mockito.never()).save(any());
        }
    }



    @Nested
    @DisplayName("Testes do metodo listarSolicitacoes")
    class MetodoListar {

        @Test
        @DisplayName("Deve retornar lista com solicitações existentes")
        void deveRetornarListaComSolicitacoes() {

            Solicitacao s1 = new Solicitacao();
            s1.setDescricao("Descricao 1");

            Solicitacao s2 = new Solicitacao();
            s2.setDescricao("Descricao 2");

            Mockito.when(solicitacaoPort.findAll()).thenReturn(List.of(s1, s2));

            List<Solicitacao> resultado = service.listarSolicitacoes();

            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(2, resultado.size());
            Mockito.verify(solicitacaoPort, Mockito.times(1)).findAll();
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando não houver solicitações")
        void deveRetornarListaVazia() {

            Mockito.when(solicitacaoPort.findAll()).thenReturn(List.of());

            List<Solicitacao> resultado = service.listarSolicitacoes();

            Assertions.assertNotNull(resultado);
            Assertions.assertTrue(resultado.isEmpty());
        }
    }



    @Nested
    @DisplayName("Testes do metodo excluirSolicitacao")
    class MetodoExcluir {

        @Test
        @DisplayName("Deve excluir corretamente quando solicitação existir")
        void deveExcluirCorretamente() {

            Integer id = 1;
            Solicitacao solicitacao = new Solicitacao();

            Mockito.when(solicitacaoPort.findById(id)).thenReturn(Optional.of(solicitacao));

            Assertions.assertDoesNotThrow(() -> service.excluirSolicitacao(id));

            Mockito.verify(solicitacaoPort, Mockito.times(1)).delete(solicitacao);
        }

        @Test
        @DisplayName("Deve lançar Exception quando solicitação não for encontrada")
        void deveLancarExceptionSolicitacaoNaoEncontrada() {

            Integer id = 99;

            Mockito.when(solicitacaoPort.findById(id)).thenReturn(Optional.empty());

            Assertions.assertThrows(EntidadeNaoExisteException.class, () ->
                    service.excluirSolicitacao(id));

            Mockito.verify(solicitacaoPort, Mockito.never()).delete(any());
        }
    }


    @Nested
    @DisplayName("Testes do metodo avaliar")
    class MetodoAvaliar {

        @Test
        @DisplayName("Deve aceitar solicitação corretamente")
        void deveAceitarSolicitacaoCorretamente() {

            Integer id = 1;
            Solicitacao solicitacao = new Solicitacao();

            Solicitacao solicitacaoSalva = new Solicitacao();
            solicitacaoSalva.setIsAceito(true);

            Mockito.when(solicitacaoPort.findById(id)).thenReturn(Optional.of(solicitacao));
            Mockito.when(solicitacaoPort.save(any(Solicitacao.class))).thenReturn(solicitacaoSalva);

            Solicitacao resultado = service.avaliar(id, true);

            Assertions.assertNotNull(resultado);
            Assertions.assertTrue(resultado.getIsAceito());
            Mockito.verify(solicitacaoPort, Mockito.times(1)).save(any(Solicitacao.class));
        }

        @Test
        @DisplayName("Deve rejeitar solicitação corretamente")
        void deveRejeitarSolicitacaoCorretamente() {

            Integer id = 1;
            Solicitacao solicitacao = new Solicitacao();

            Solicitacao solicitacaoSalva = new Solicitacao();
            solicitacaoSalva.setIsAceito(false);

            Mockito.when(solicitacaoPort.findById(id)).thenReturn(Optional.of(solicitacao));
            Mockito.when(solicitacaoPort.save(any(Solicitacao.class))).thenReturn(solicitacaoSalva);

            Solicitacao resultado = service.avaliar(id, false);

            Assertions.assertNotNull(resultado);
            Assertions.assertFalse(resultado.getIsAceito());
        }

        @Test
        @DisplayName("Deve lançar Exception quando solicitação não for encontrada")
        void deveLancarExceptionSolicitacaoNaoEncontrada() {

            Integer id = 99;

            Mockito.when(solicitacaoPort.findById(id)).thenReturn(Optional.empty());

            // avaliar lança EntidadeInvalidException conforme a service atual
            Assertions.assertThrows(EntidadeInvalidException.class, () ->
                    service.avaliar(id, true));

            Mockito.verify(solicitacaoPort, Mockito.never()).save(any());
        }
    }



    @Nested
    @DisplayName("Testes do metodo listarSolicitacoesBoolean")
    class MetodoListarBoolean {

        @Test
        @DisplayName("Deve retornar página de solicitações aceitas")
        void deveRetornarSolicitacoesAceitas() {

            Solicitacao s1 = new Solicitacao();
            s1.setIsAceito(true);

            Pageable pageable = PageRequest.of(0, 10);
            Page<Solicitacao> pageFake = new PageImpl<>(List.of(s1));

            Mockito.when(solicitacaoPort.findByIsAceito(true, pageable)).thenReturn(pageFake);

            Page<Solicitacao> resultado = service.listarSolicitacoesBoolean(true, pageable);

            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.getTotalElements());
            Assertions.assertTrue(resultado.getContent().get(0).getIsAceito());
            Mockito.verify(solicitacaoPort, Mockito.times(1)).findByIsAceito(true, pageable);
        }

        @Test
        @DisplayName("Deve retornar página de solicitações rejeitadas")
        void deveRetornarSolicitacoesRejeitadas() {

            Solicitacao s1 = new Solicitacao();
            s1.setIsAceito(false);

            Pageable pageable = PageRequest.of(0, 10);
            Page<Solicitacao> pageFake = new PageImpl<>(List.of(s1));

            Mockito.when(solicitacaoPort.findByIsAceito(false, pageable)).thenReturn(pageFake);

            Page<Solicitacao> resultado = service.listarSolicitacoesBoolean(false, pageable);

            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.getTotalElements());
            Assertions.assertFalse(resultado.getContent().get(0).getIsAceito());
        }

        @Test
        @DisplayName("Deve retornar página vazia quando não houver resultados")
        void deveRetornarPaginaVazia() {

            Pageable pageable = PageRequest.of(0, 10);
            Page<Solicitacao> paginaVazia = new PageImpl<>(List.of());

            Mockito.when(solicitacaoPort.findByIsAceito(true, pageable)).thenReturn(paginaVazia);

            Page<Solicitacao> resultado = service.listarSolicitacoesBoolean(true, pageable);

            Assertions.assertNotNull(resultado);
            Assertions.assertTrue(resultado.isEmpty());
        }
    }
}