package school.sptech.sistema_estoque.dto.estoque.almoxarife;

public record AlmoxarifeUpdateRequest(
        String nome,
        String telefone,
        String senha,
        Integer idAlmoxarifado
) {
}
