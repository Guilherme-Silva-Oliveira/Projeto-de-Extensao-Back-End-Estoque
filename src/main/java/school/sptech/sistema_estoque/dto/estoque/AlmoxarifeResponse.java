package school.sptech.sistema_estoque.dto.estoque;

public record AlmoxarifeResponse(
        Integer id,
        String nome,
        String email,
        String telefone,
        String senha,
        Integer idAlmoxarifado,
        Integer idEstoque,
        AlmoxarifadoResponse almoxarifado,
        EstoqueResponse estoque
) {
}
