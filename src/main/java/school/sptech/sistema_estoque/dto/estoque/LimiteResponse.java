package school.sptech.sistema_estoque.dto.estoque;

public record LimiteResponse(
        Integer id,
        String limite,
        TipoLimiteResponse tipoLimite
) {}