package school.sptech.sistema_estoque.dto.estoque;

import java.util.List;

public record AlmoxarifadoResponse(
        Integer id,
        Integer numeroSala,
        List<Integer> idsLimites,
        List<LimiteResponse> limites
) {
}
