package school.sptech.sistema_estoque.dto.estoque;

import java.time.LocalDateTime;

public record SolicitacaoRequest(
        Integer idProfessor,
        String escala,
        String descricao,
        LocalDateTime dataSolicitacao
) {

}
