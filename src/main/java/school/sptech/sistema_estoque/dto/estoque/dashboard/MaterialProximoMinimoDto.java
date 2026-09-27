package school.sptech.sistema_estoque.dto.estoque.dashboard;

public record MaterialProximoMinimoDto(
        String nomeMaterial,
        Integer quantidadeAtual,
        Integer quantidadeMinima,
        Integer diferenca
) {
}