package school.sptech.sistema_estoque.dto.ia;

public record SolicitacaoIARequest(
        String nome_professor,
        String nome_material,
        Integer quantidade,
        String data_solicitacao
) {}
