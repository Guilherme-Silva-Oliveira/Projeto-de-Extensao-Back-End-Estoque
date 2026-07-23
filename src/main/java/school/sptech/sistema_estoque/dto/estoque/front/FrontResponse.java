package school.sptech.sistema_estoque.dto.estoque.front;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.dto.estoque.tipo_limite.TipoLimiteResponse;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.ListaMaterial;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

import java.time.LocalDateTime;
import java.util.List;

public record FrontResponse(
        @Schema(description = "Descrição Solicitação") String descricaoSolicitacao,
        @Schema(description = "Data Solicitação") LocalDateTime dataSolicitacao,
        @Schema(description = "Data Envio") LocalDateTime dataEnvio,
        @Schema(description = "Nome Professor") String professor,
        @Schema(description = "Nome Materiais") List<String> materiais,
        @Schema(description = "Descrição Alerta de Devolução") List<String> alertaDevolucao
) {}