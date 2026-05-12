package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialUpdateRequest;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialRequest;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialResponse;
import school.sptech.sistema_estoque.dto.mapper.MaterialMapper;
import school.sptech.sistema_estoque.exception.EntidadeConflictException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Categoria;
import school.sptech.sistema_estoque.model.estoque.CodigoBarras;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;
import school.sptech.sistema_estoque.port.*;
import school.sptech.sistema_estoque.repository.CodigoBarrasRepository;
import school.sptech.sistema_estoque.repository.UnidadeMedidaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    private final MaterialPort materialPort;;
    private final CategoriaPort categoriaPort;
    private final AlmoxarifadoPort almoxarifadoPort;
    private final UnidadeMedidaPort unidadeMedidaPort;
    private final CodigoBarrasPort codigoBarrasPort;

    public MaterialService(MaterialPort materialPort, CategoriaPort categoriaPort,
                           AlmoxarifadoPort almoxarifadoPort, UnidadeMedidaPort unidadeMedidaPort,
                           CodigoBarrasPort codigoBarrasPort) {
        this.materialPort = materialPort;
        this.categoriaPort = categoriaPort;
        this.almoxarifadoPort = almoxarifadoPort;
        this.unidadeMedidaPort = unidadeMedidaPort;
        this.codigoBarrasPort = codigoBarrasPort; ;
    }

    public Material cadastrarMaterial(MaterialRequest request){
        if (request==null){throw new EntidadeInvalidException("Material Inválido");}
        if (materialPort.existsByNomeMaterialAndAlmoxarifadoId(request.nomeMaterial(), request.idAlmoxarifado())){
            throw new EntidadeConflictException("Já existe um Material cadastrado com esse email e id de material");
        }        Optional<CodigoBarras> codigoExistente = codigoBarrasPort.findById(request.codigoBarras());;
        if (codigoExistente.isPresent()) {
            return codigoExistente.get().getMaterial();
        }

        Optional<Categoria> catOpt = categoriaPort.findById(request.idCategoria());
        if (catOpt.isEmpty()){throw new EntidadeInvalidException("Categoria Não Encontrada");}
        Optional<Almoxarifado> estOpt = almoxarifadoPort.findById(request.idAlmoxarifado());
        if (estOpt.isEmpty()){throw new EntidadeInvalidException("Estoque Não Encontrado");}
        Optional<UnidadeMedida> uniOpt = unidadeMedidaPort.findById(request.idUnidadeMedida());
        if (uniOpt.isEmpty()){throw new EntidadeInvalidException("Unidade de Medida Não Encontrada");}

        Categoria c = catOpt.get();
        Almoxarifado a = estOpt.get();
        UnidadeMedida u = uniOpt.get();
        Material m = new Material(null, request.nomeMaterial(), c, a, u, 0);
        Material salvo = materialPort.save(m);
        codigoBarrasPort.save(new CodigoBarras(request.codigoBarras(), salvo));
        return salvo;
    }

    public List<Material> listarMateriais(){
        return materialPort.findAll();
    }

    public void excluirMaterial(Integer id){
        Optional<Material> opt = materialPort.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Material Não Encontrado");}
        materialPort.delete(opt.get());
    }

    public MaterialResponse atualizarParcial(Integer id, MaterialUpdateRequest request) {

        Material material = materialPort.findById(id)
                .orElseThrow(() -> new EntidadeInvalidException("Material não encontrado"));

        if (request.nomeMaterial() != null) {
            material.setNomeMaterial(request.nomeMaterial());
        }

        if (request.quantidade() != null) {
            material.setQuantidade(request.quantidade());
        }

        Material salvo = materialPort.save(material);
        return MaterialMapper.toResponse(salvo);
    }
}