package school.sptech.sistema_estoque.dto.estoque;

public record FornecedorResponse(
        Integer id,
        String nome,
        String email,
        String telefone,
        String cnpjCpf,
        Integer idTipoFornecedor,
        TipoFornecedorResponse tipoFornecedor
) {
}
