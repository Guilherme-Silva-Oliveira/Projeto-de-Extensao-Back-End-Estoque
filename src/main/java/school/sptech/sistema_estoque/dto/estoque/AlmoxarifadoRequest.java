package school.sptech.sistema_estoque.dto.estoque;

import java.util.List;

public record AlmoxarifadoRequest(
        Integer numeroSala,
        List<Integer> idsLimites
) {
}
