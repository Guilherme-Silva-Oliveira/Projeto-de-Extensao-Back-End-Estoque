package school.sptech.sistema_estoque.dto.mq;

import java.time.LocalDateTime;

public record AlertaMessage(
        String tipo,
        Integer referenciaId,
        String nome,
        String descricao,
        LocalDateTime criadoEm
) {}
