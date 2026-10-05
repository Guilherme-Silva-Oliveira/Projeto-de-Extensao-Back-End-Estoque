package school.sptech.sistema_estoque.dto.estoque.almoxarife;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlmoxarifeSenhaRequest(
        @NotBlank String senhaAntiga,
        @NotBlank @Size(min = 6) String senhaNova
) {
}