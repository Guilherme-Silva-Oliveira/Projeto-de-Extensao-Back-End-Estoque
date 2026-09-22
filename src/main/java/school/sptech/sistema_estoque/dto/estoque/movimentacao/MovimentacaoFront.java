package school.sptech.sistema_estoque.dto.estoque.movimentacao;

import java.time.LocalDateTime;

public record MovimentacaoFront(
    String acao,
    String material,
    String motivoFornecedor,
    LocalDateTime dataMovimentacao,
    Integer quantidade
) {}
