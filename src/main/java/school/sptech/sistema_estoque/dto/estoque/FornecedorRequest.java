package school.sptech.sistema_estoque.dto.estoque;

public record FornecedorRequest(
        String nome,
        String email,
        String telefone,
        String cnpjCpf,
        Integer idTipoFornecedor
) {
}
