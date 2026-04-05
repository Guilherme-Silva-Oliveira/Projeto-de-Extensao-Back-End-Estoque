package school.sptech.sistema_estoque.dto.estoque;

public record LimiteRequest(
        String limite,
        Integer idTipoLimite
) {}