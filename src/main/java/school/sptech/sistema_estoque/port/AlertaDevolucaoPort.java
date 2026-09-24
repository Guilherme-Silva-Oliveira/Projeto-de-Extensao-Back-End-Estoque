
package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.dto.classapp.LabelsRequest;
import school.sptech.sistema_estoque.dto.classapp.TagsRequest;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.model.estoque.ListaMaterial;

import java.util.List;
import java.util.Optional;

public interface AlertaDevolucaoPort {
    List<AlertaDevolucao> findAll();
    Optional<AlertaDevolucao> findById(Integer id);
}
