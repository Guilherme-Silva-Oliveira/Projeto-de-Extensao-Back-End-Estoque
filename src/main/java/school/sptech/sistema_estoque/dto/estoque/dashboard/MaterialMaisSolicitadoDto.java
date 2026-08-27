package school.sptech.sistema_estoque.dto.estoque.dashboard;

import java.time.LocalDateTime;
public record MaterialMaisSolicitadoDto(
        String nomeMaterial,
        Long totalSolicitado,
        LocalDateTime dataInicio,
        LocalDateTime dataFim
) {
    // Construtor auxiliar para o JPQL
    public MaterialMaisSolicitadoDto(String nomeMaterial, Long totalSolicitado) {
        this(nomeMaterial, totalSolicitado, null, null);
    }
}