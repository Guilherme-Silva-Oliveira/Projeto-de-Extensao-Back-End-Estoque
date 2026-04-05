package school.sptech.sistema_estoque.dto.estoque;

public record AlmoxarifeRequest(
        String nome,
        String email,
        String telefone,
        String senha,
        Integer idAlmoxarifado,
        Integer idEstoque
) {
}
