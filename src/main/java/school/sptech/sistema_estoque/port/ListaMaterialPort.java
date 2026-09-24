package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.ListaMaterial;
import school.sptech.sistema_estoque.model.estoque.SetorEstoque;

import java.util.List;
import java.util.Optional;

public interface ListaMaterialPort {
    ListaMaterial save(ListaMaterial listaMaterial);
    Optional<ListaMaterial> findById(Integer id);
    List<ListaMaterial> findAll();
    List<Optional<ListaMaterial>> findBySolicitacaoId(Integer solicitacaoId);
}