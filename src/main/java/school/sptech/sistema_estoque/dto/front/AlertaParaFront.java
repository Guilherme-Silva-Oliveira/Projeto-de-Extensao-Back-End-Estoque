package school.sptech.sistema_estoque.dto.front;

import school.sptech.sistema_estoque.model.estoque.Solicitacao;

public record AlertaParaFront(
    String causaAlerta,
    String nomeProfessor,
    String descricao,
    Boolean isResolvido,
    Integer idAlerta
) {}