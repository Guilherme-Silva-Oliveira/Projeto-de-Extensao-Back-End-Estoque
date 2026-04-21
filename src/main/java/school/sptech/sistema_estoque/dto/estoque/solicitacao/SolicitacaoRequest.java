package school.sptech.sistema_estoque.dto.estoque.solicitacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SolicitacaoRequest(
        @NotNull @Schema(description = "Fk para Professor",example = "1") Integer idProfessor,
        @NotBlank @Schema(description = "Motivo da Solicitação",example = "Atividade Avaliativa")String motivo,
        @NotNull @Schema(description = "Data da Solicitação",example = "20-04-2026") LocalDateTime dataSolicitacao
) {}
