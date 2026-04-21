package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Categoria;
import school.sptech.sistema_estoque.model.estoque.CodigoBarras;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;
import school.sptech.sistema_estoque.repository.AlmoxarifadoRepository;
import school.sptech.sistema_estoque.repository.CategoriaRepository;
import school.sptech.sistema_estoque.repository.CodigoBarrasRepository;
import school.sptech.sistema_estoque.repository.MaterialRepository;
import school.sptech.sistema_estoque.repository.UnidadeMedidaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    private final MaterialRepository matrepository;
    private final CategoriaRepository catrepository;
    private final AlmoxarifadoRepository almrepository;
    private final UnidadeMedidaRepository unirepository;
    private final CodigoBarrasRepository codigoBarrasRepository;

    public MaterialService(MaterialRepository matrepository, CategoriaRepository catrepository,
                           AlmoxarifadoRepository almrepository, UnidadeMedidaRepository unirepository,
                           CodigoBarrasRepository codigoBarrasRepository) {
        this.matrepository = matrepository;
        this.catrepository = catrepository;
        this.almrepository = almrepository;
        this.unirepository = unirepository;
        this.codigoBarrasRepository = codigoBarrasRepository;
    }

    public Material cadastrarMaterial(MaterialRequest request){
        if (request==null){throw new EntidadeInvalidException("Material Inválido");}

        Optional<CodigoBarras> codigoExistente = codigoBarrasRepository.findById(request.codigoBarras());
        if (codigoExistente.isPresent()) {
            return codigoExistente.get().getMaterial();
        }

        Optional<Categoria> catOpt = catrepository.findById(request.idCategoria());
        if (catOpt.isEmpty()){throw new EntidadeInvalidException("Categoria Não Encontrada");}
        Optional<Almoxarifado> estOpt = almrepository.findById(request.idAlmoxarifado());
        if (estOpt.isEmpty()){throw new EntidadeInvalidException("Estoque Não Encontrado");}
        Optional<UnidadeMedida> uniOpt = unirepository.findById(request.idUnidadeMedida());
        if (uniOpt.isEmpty()){throw new EntidadeInvalidException("Unidade de Medida Não Encontrada");}

        Categoria c = catOpt.get();
        Almoxarifado a = estOpt.get();
        UnidadeMedida u = uniOpt.get();
        Material m = new Material(null, request.nomeMaterial(), c, a, u, 0);
        Material salvo = matrepository.save(m);
        codigoBarrasRepository.save(new CodigoBarras(request.codigoBarras(), salvo));
        return salvo;
    }

    public List<Material> listarMateriais(){
        return matrepository.findAll();
    }

    public void excluirMaterial(Integer id){
        Optional<Material> opt = matrepository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Material Não Encontrado");}
        matrepository.delete(opt.get());
    }
}
