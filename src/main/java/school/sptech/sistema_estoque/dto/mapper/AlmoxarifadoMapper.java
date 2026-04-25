package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;

public class AlmoxarifadoMapper {

    public static Almoxarifado toEntity(AlmoxarifadoRequest request) {
        Almoxarifado a = new Almoxarifado();
        a.setNumeroSala(request.numeroSala());
        return a;
    }

    public static AlmoxarifadoResponse toResponse(Almoxarifado entity) {
        return new AlmoxarifadoResponse(
                entity.getId(),
                entity.getNumeroSala()
        );
    }
}
