package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteResponse;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Limite;

public class AlmoxarifadoMapper {
    public static Almoxarifado toEntity(AlmoxarifadoRequest request, java.util.List<Limite> limites) {
        Almoxarifado a = new Almoxarifado();
        a.setNumeroSala(request.numeroSala());
        a.setLimites(limites);
        return a;
    }

    public static AlmoxarifadoResponse toResponse(Almoxarifado entity) {
        java.util.List<Integer> limiteIds = entity.getLimites() == null
                ? java.util.List.of()
                : entity.getLimites().stream().map(Limite::getId).toList();
        java.util.List<LimiteResponse> limiteResponses = entity.getLimites() == null
                ? java.util.List.of()
                : entity.getLimites().stream().map(LimiteMapper::toLimiteResponse).toList();
        return new AlmoxarifadoResponse(
                entity.getId(),
                entity.getNumeroSala()
        );
    }
}
