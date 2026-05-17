package school.sptech.sistema_estoque.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.sistema_estoque.dto.estoque.professor.ProfessorPatchDto;
import school.sptech.sistema_estoque.dto.estoque.professor.ProfessorRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.port.ProfessorPort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfessorServiceTest {

    @Mock
    private ProfessorPort professorPort;

    @InjectMocks
    private ProfessorService service;



    // CADASTRAR PROFESSOR

    @Nested
    @DisplayName("Testes do Método Cadastrar")
    class MetodoCadastrar {

        // cenario feliz (cadastrou corretamente)

        @Test
        @DisplayName("Deve Cadastrar um Professor Corretamente")
        void deveCadastrarProfessorCorretamente() {

            Professor professor = new Professor();
            professor.setNome("Marina Xingu");

            ProfessorRequest request = new ProfessorRequest("Marina Xingu", "marina@gmail.com", "11999990000");

            when(professorPort.existsByEmail(request.email())).thenReturn(false);
            when(professorPort.existsByTelefone(request.telefone())).thenReturn(false);
            when(professorPort.save(any(Professor.class))).thenReturn(professor);


            Professor resultado = service.cadastrarProfessor(request);


            assertNotNull(resultado);
            assertEquals(professor.getNome(), resultado.getNome());
            verify(professorPort, times(1)).save(any(Professor.class));
        }

        //cenario triste (nulo)

        @Test
        @DisplayName("Deve Lançar Exception Quando Request Nulo")
        void deveLancarExceptionRequestNulo() {

            assertThrows(EntidadeInvalidException.class, () ->
                    service.cadastrarProfessor(null));
        }

        //cenario triste (email duplicado)

        @Test
        @DisplayName("Deve Lançar Exception Por Email Duplicado")
        void deveLancarExceptionEmailDuplicado() {

            ProfessorRequest request = new ProfessorRequest("Marina Xingu", "marina@gmail.com", "11999990000");

            when(professorPort.existsByEmail(request.email())).thenReturn(true);

            assertThrows(ResponseStatusException.class, () ->
                    service.cadastrarProfessor(request));

            verify(professorPort, never()).save(any());
        }

        //cenario triste (telefone duplicado)

        @Test
        @DisplayName("Deve Lançar Exception Por Telefone Duplicado")
        void deveLancarExceptionTelefoneDuplicado() {

            ProfessorRequest request = new ProfessorRequest("Marina Xingu", "marina@gmail.com", "11999990000");

            when(professorPort.existsByEmail(request.email())).thenReturn(false);
            when(professorPort.existsByTelefone(request.telefone())).thenReturn(true);


            assertThrows(ResponseStatusException.class, () ->
                    service.cadastrarProfessor(request));

            verify(professorPort, never()).save(any());
        }
    }



    // LISTAR PROFESSOR

    @Nested
    @DisplayName("Testes do Método Listar")
    class MetodoListar {

        //cenario feliz (listar corretamente)

        @Test
        @DisplayName("Deve Listar Professores Corretamente")
        void deveListarProfessoresCorretamente() {

            Professor professor = new Professor();
            professor.setNome("Marina Xingu");

            when(professorPort.findAll()).thenReturn(List.of(professor));


            List<Professor> resultado = service.listarProfessor();


            assertNotNull(resultado);
            assertFalse(resultado.isEmpty());
            assertEquals(professor.getNome(), resultado.getFirst().getNome());
        }

        // cenario triste (nenhum professor cadastrado)

        @Test
        @DisplayName("Deve Lançar Exception Quando Não Há Professores Cadastrados")
        void deveLancarExceptionQuandoNaoHaProfessoresCadastrados() {

            when(professorPort.findAll()).thenReturn(List.of());


            assertThrows(EntidadeNaoExisteException.class, () ->
                    service.listarProfessor());
        }
    }



    // EXCLUIR PROFESSOR

    @Nested
    @DisplayName("Testes do Método Excluir")
    class MetodoExcluir {

        //cenario feliz (excluir corretamente)

        @Test
        @DisplayName("Deve Excluir Professor Corretamente")
        void deveExcluirProfessor() {

            Professor professor = new Professor();
            professor.setId(1);

            when(professorPort.findById(1)).thenReturn(Optional.of(professor));


            service.excluirProfessor(1);


            verify(professorPort, times(1)).delete(professor);
        }


        //cenario triste  (professor não encontrado)

        @Test
        @DisplayName("Deve Lançar Exception Por Professor Não Encontrado")
        void deveLancarExceptionProfessorNaoEncontrado() {

            when(professorPort.findById(anyInt())).thenReturn(Optional.empty());


            assertThrows(EntidadeNaoExisteException.class, () ->
                    service.excluirProfessor(99));

            verify(professorPort, never()).delete(any());
        }
    }



    // ATUALIZAR PROFESSOR



    @Nested
    @DisplayName("Testes do Método Atualizar")

            //cenario feliz (atualizar corretamente)

    class MetodoAtualizar {

        @Test
        @DisplayName("Deve Atualizar Professor Corretamente")
        void deveAtualizarProfessor() {

            Professor professor = new Professor();
            professor.setNome("Marina Xingu");
            professor.setEmail("marina@gmail.com");
            professor.setTelefone("11999990000");

            Professor professorAtualizado = new Professor();
            professorAtualizado.setNome("João Atualizado");
            professorAtualizado.setEmail("novo@gmail.com");
            professorAtualizado.setTelefone("11888880000");

            ProfessorPatchDto request = new ProfessorPatchDto("João Atualizado", "novo@gmail.com", "11888880000");

            when(professorPort.findById(1)).thenReturn(Optional.of(professor));
            when(professorPort.save(any(Professor.class))).thenReturn(professorAtualizado);


            Professor resultado = service.atualizarProfessor(1, request);


            assertNotNull(resultado);
            assertEquals(professorAtualizado.getNome(), resultado.getNome());
            assertEquals(professorAtualizado.getEmail(), resultado.getEmail());
            verify(professorPort, times(1)).save(any(Professor.class));
        }


        //cenario triste (professor nao encontrado)

        @Test
        @DisplayName("Deve Lançar Exception Por Professor Não Encontrado ao Atualizar")
        void deveLancarExceptionProfessorNaoEncontradoAoAtualizar() {

            ProfessorPatchDto request = new ProfessorPatchDto("João Atualizado", null, null);

            when(professorPort.findById(anyInt())).thenReturn(Optional.empty());


            assertThrows(EntidadeNaoExisteException.class, () ->
                    service.atualizarProfessor(99, request));

            verify(professorPort, never()).save(any());
        }

        // cenario triste (campos nulos)

        @Test
        @DisplayName("Não Deve Alterar Campos Com Valores Nulos")
        void deveNaoAtualizarCamposNulos() {

            Professor professor = new Professor();
            professor.setNome("Marina Xingu");
            professor.setEmail("marina@gmail.com");
            professor.setTelefone("11999990000");

            ProfessorPatchDto request = new ProfessorPatchDto(null, null, null);

            when(professorPort.findById(1)).thenReturn(Optional.of(professor));
            when(professorPort.save(any(Professor.class))).thenReturn(professor);


            Professor resultado = service.atualizarProfessor(1, request);

            assertEquals("Marina Xingu", resultado.getNome());
            assertEquals("marina@gmail.com", resultado.getEmail());
            assertEquals("11999990000", resultado.getTelefone());
        }
    }
}