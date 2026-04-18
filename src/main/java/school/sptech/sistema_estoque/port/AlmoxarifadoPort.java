package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Almoxarifado;

import java.util.List;

public interface AlmoxarifadoPort {
    Almoxarifado save(Almoxarifado almoxarifado);
    List<Almoxarifado> findAll();
}
