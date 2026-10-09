package school.sptech.sistema_estoque.dto.estoque.front;

import io.swagger.v3.oas.annotations.media.Schema;

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