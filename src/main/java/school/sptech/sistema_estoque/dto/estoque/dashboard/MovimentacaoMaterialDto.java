package school.sptech.sistema_estoque.dto.estoque.dashboard;

import jakarta.transaction.Transactional;

@Transactional
public record MovimentacaoMaterialDto(String nomeMaterial, Long entradas, Long saidas) {
}