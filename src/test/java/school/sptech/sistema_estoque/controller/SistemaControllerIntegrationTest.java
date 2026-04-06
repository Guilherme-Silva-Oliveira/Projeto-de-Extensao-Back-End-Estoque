package school.sptech.sistema_estoque.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.sistema_estoque.dto.estoque.*;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.repository.AlmoxarifadoRepository;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class SistemaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AlmoxarifadoRepository almoxarifadoRepository;

    @Test
    void postFornecedorRelacionaTipoFornecedor() throws Exception {
        JsonNode tipoFornecedorJson = postJson("/v1/fornecedores/tipos", new TipoFornecedorRequest("Fornecedor"));
        int tipoFornecedorId = tipoFornecedorJson.get("id").asInt();

        FornecedorRequest fornecedorRequest = new FornecedorRequest(
                "Fornecedor X",
                "fornecedor@example.com",
                "11999999999",
                "12345678000199",
                tipoFornecedorId
        );

        mockMvc.perform(post("/v1/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fornecedorRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.idTipoFornecedor").value(tipoFornecedorId))
                .andExpect(jsonPath("$.tipoFornecedor.id").value(tipoFornecedorId));
    }

    @Test
    void postMaterialRelacionaCategoriaAlmoxarifadoUnidade() throws Exception {
        JsonNode categoriaJson = postJson("/v1/categorias", new CategoriaRequest("Informatica"));
        int categoriaId = categoriaJson.get("id").asInt();

        JsonNode unidadeJson = postJson("/v1/unidadeMedida", new UnidadeMedidaRequest("Unidade"));
        int unidadeId = unidadeJson.get("id").asInt();

        Almoxarifado almoxarifado = new Almoxarifado();
        almoxarifado.setNumeroSala(1);
        almoxarifado = almoxarifadoRepository.save(almoxarifado);

        MaterialRequest materialRequest = new MaterialRequest(
                categoriaId,
                almoxarifado.getId(),
                "Mouse",
                unidadeId
        );

        mockMvc.perform(post("/v1/materiais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(materialRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.categoria.id").value(categoriaId));
    }

    @Test
    void postSolicitacaoRelacionaProfessor() throws Exception {
        JsonNode professorJson = postJson("/v1/professores",
                new ProfessorRequest("Ana", "ana@example.com", "11988887777"));
        int professorId = professorJson.get("id").asInt();

        SolicitacaoRequest solicitacaoRequest = new SolicitacaoRequest(
                professorId,
                "Manha",
                "Reposicao de materiais",
                LocalDateTime.of(2026, 4, 5, 10, 0)
        );

        mockMvc.perform(post("/v1/solicitacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(solicitacaoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.professor.id").value(professorId));
    }

    @Test
    void postPedidoEntradaRelacionaFornecedorEMaterial() throws Exception {
        JsonNode tipoFornecedorJson = postJson("/v1/fornecedores/tipos", new TipoFornecedorRequest("Fornecedor"));
        int tipoFornecedorId = tipoFornecedorJson.get("id").asInt();

        JsonNode fornecedorJson = postJson("/v1/fornecedores", new FornecedorRequest(
                "Fornecedor Y",
                "fornecedor2@example.com",
                "11911112222",
                "98765432000199",
                tipoFornecedorId
        ));
        int fornecedorId = fornecedorJson.get("id").asInt();

        JsonNode categoriaJson = postJson("/v1/categorias", new CategoriaRequest("Laboratorio"));
        int categoriaId = categoriaJson.get("id").asInt();
        JsonNode unidadeJson = postJson("/v1/unidadeMedida", new UnidadeMedidaRequest("Caixa"));
        int unidadeId = unidadeJson.get("id").asInt();
        Almoxarifado almoxarifado = new Almoxarifado();
        almoxarifado.setNumeroSala(2);
        almoxarifado = almoxarifadoRepository.save(almoxarifado);

        JsonNode materialJson = postJson("/v1/materiais", new MaterialRequest(
                categoriaId,
                almoxarifado.getId(),
                "Luva",
                unidadeId
        ));
        int materialId = materialJson.get("id").asInt();

        PedidoEntradaRequest pedidoEntradaRequest = new PedidoEntradaRequest(
                fornecedorId,
                materialId,
                20,
                LocalDateTime.of(2026, 4, 5, 9, 30)
        );

        mockMvc.perform(post("/v1/pedido-entrada")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoEntradaRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fornecedorId").value(fornecedorId))
                .andExpect(jsonPath("$.materialId").value(materialId))
                .andExpect(jsonPath("$.fornecedor.id").value(fornecedorId))
                .andExpect(jsonPath("$.material.id").value(materialId));
    }

    @Test
    void postPedidoSaidaRelacionaMaterialSolicitacaoEscala() throws Exception {
        JsonNode categoriaJson = postJson("/v1/categorias", new CategoriaRequest("Consumo"));
        int categoriaId = categoriaJson.get("id").asInt();
        JsonNode unidadeJson = postJson("/v1/unidadeMedida", new UnidadeMedidaRequest("Pacote"));
        int unidadeId = unidadeJson.get("id").asInt();
        Almoxarifado almoxarifado = new Almoxarifado();
        almoxarifado.setNumeroSala(3);
        almoxarifado = almoxarifadoRepository.save(almoxarifado);
        JsonNode materialJson = postJson("/v1/materiais", new MaterialRequest(
                categoriaId,
                almoxarifado.getId(),
                "Papel",
                unidadeId
        ));
        int materialId = materialJson.get("id").asInt();

        JsonNode professorJson = postJson("/v1/professores",
                new ProfessorRequest("Beatriz", "bia@example.com", "11977776666"));
        int professorId = professorJson.get("id").asInt();

        JsonNode solicitacaoJson = postJson("/v1/solicitacoes", new SolicitacaoRequest(
                professorId,
                "Noite",
                "Retirada para aula",
                LocalDateTime.of(2026, 4, 5, 18, 0)
        ));
        int solicitacaoId = solicitacaoJson.get("id").asInt();

        JsonNode escalaJson = postJson("/v1/escalas", new EscalaRequest("Escala A"));
        int escalaId = escalaJson.get("id").asInt();

        PedidoSaidaRequest pedidoSaidaRequest = new PedidoSaidaRequest(
                materialId,
                solicitacaoId,
                5,
                LocalDateTime.of(2026, 4, 5, 18, 0),
                escalaId,
                LocalDateTime.of(2026, 4, 6, 8, 0)
        );

        mockMvc.perform(post("/v1/pedido-saida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoSaidaRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.material.id").value(materialId))
                .andExpect(jsonPath("$.solicitacao.id").value(solicitacaoId))
                .andExpect(jsonPath("$.escala.id").value(escalaId));
    }

    @Test
    void postAlmoxarifeRelacionaAlmoxarifado() throws Exception {
        JsonNode tipoLimiteJson = postJson("/v1/tipos-limite", new TipoLimiteRequest("Unidade"));
        int tipoLimiteId = tipoLimiteJson.get("id").asInt();

        JsonNode limiteJson = postJson("/v1/limites", new LimiteRequest("100", tipoLimiteId));
        int limiteId = limiteJson.get("id").asInt();

        JsonNode almoxarifadoJson = postJson("/v1/almoxarifados", new AlmoxarifadoRequest(12, java.util.List.of(limiteId)));
        int almoxarifadoId = almoxarifadoJson.get("id").asInt();

        AlmoxarifeRequest almoxarifeRequest = new AlmoxarifeRequest(
                "Carlos",
                "carlos@example.com",
                "11933334444",
                "senha123",
                almoxarifadoId
        );

        mockMvc.perform(post("/v1/almoxarifes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(almoxarifeRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.idAlmoxarifado").value(almoxarifadoId))
                .andExpect(jsonPath("$.almoxarifado.idsLimites[0]").value(limiteId));
    }

    private JsonNode postJson(String url, Object body) throws Exception {
        String response = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        return objectMapper.readTree(response);
    }
}
