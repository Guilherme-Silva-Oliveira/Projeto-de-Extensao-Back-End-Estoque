package school.sptech.sistema_estoque.dto.estoque;

import school.sptech.sistema_estoque.model.estoque.Professor;

import java.time.LocalDateTime;

public record SolicitacaoResponse(
        Integer id,
        Professor professor,
        String escala,
        String descricao,
        LocalDateTime dataSolicitacao
) {
}
