package school.sptech.sistema_estoque.dto.estoque.material;

import java.time.LocalDate;

public record MaterialUpdateRequest(
        String nomeMaterial,
        Integer quantidade,
        String descricao,
        LocalDate dataVencimento
) {
}
