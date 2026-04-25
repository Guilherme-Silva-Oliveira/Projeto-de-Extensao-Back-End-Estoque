package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Almoxarifado;

import java.util.List;
import java.util.Optional;

public interface AlmoxarifadoPort {

    Almoxarifado save(Almoxarifado almoxarifado);

    List<Almoxarifado> findAll();

    Optional<Almoxarifado> findByNumeroSala(Integer numeroSala);

    Optional<Almoxarifado> findById(Integer id);

    void delete(Almoxarifado almoxarifado);

}