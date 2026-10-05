package school.sptech.sistema_estoque.dto.estoque.almoxarife;

import jakarta.validation.constraints.Email;

public record AlmoxarifeUpdateRequest(
        String nome,
        @Email String email,
        String telefone,
        Integer idAlmoxarifado
) {
}
