package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.SetorEstoque;

import java.util.List;
import java.util.Optional;

public interface SetorEstoquePort {
    Optional<SetorEstoque> findByIdentificadorSetor(String identificador);
}