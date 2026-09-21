package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.dto.estoque.setor_estoque.SetorEstoqueResponse;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.SetorEstoque;

public class SetorEstoqueMapper {

    public static SetorEstoqueResponse toResponse(SetorEstoque entity) {
        return new SetorEstoqueResponse(
                entity.getAlmoxarifado().getId(),
                entity.getIdentificadorSetor()
        );
    }
}
