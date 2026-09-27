package school.sptech.sistema_estoque.dto.estoque.lista_material;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.dto.estoque.categoria.CategoriaResponse;
import school.sptech.sistema_estoque.dto.estoque.unidade_medida.UnidadeMedidaResponse;

import java.time.LocalDate;

public record ListaMaterialResponse(
        @Schema(description = "ID da linha na lista de materiais (lista_material), usado para marcar entrega") Integer id,
        @Schema(description = "Material Associado") String material,
        @Schema(description = "Quantidade Solicitada") Integer quantidadeSolicitada,
        @Schema(description = "Quantidade Disponível") Integer quantidadeDisponivel,
        @Schema(description = "Indica se este item já foi entregue") Boolean entregue
) {}
