package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeResponse;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Almoxarife;

public class AlmoxarifeMapper {
    public static Almoxarife toEntity(AlmoxarifeRequest request, Almoxarifado almoxarifado) {
        Almoxarife a = new Almoxarife();
        a.setNome(request.nome());
        a.setEmail(request.email());
        a.setTelefone(request.telefone());
        a.setSenha(request.senha());
        a.setAlmoxarifado(almoxarifado);
        return a;
    }

    public static AlmoxarifeResponse toResponse(Almoxarife entity) {
        AlmoxarifadoResponse almoxarifadoResponse = entity.getAlmoxarifado() != null ? toAlmoxarifadoResponse(entity.getAlmoxarifado()) : null;
        return new AlmoxarifeResponse(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                almoxarifadoResponse
        );
    }
}
