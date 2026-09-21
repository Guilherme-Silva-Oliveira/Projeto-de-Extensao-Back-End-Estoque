package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.alerta_devolucao.AlertaDevolucaoResponse;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;

public class AlertaDevolucaoMapper {

    public static AlertaDevolucaoResponse toResponse(AlertaDevolucao entity) {
        return new AlertaDevolucaoResponse(
                entity.getSolicitacao().getId()
        );
    }
}
