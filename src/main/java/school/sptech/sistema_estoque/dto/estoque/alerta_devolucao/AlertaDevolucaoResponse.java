package school.sptech.sistema_estoque.dto.estoque.alerta_devolucao;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

public record AlertaDevolucaoResponse(
        @Schema(description = "Solicitacao Associada") Integer solicitacao
) { }
