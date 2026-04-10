package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.MaterialRequest;
import school.sptech.sistema_estoque.dto.estoque.MaterialResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.exception.*;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Categoria;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;
import school.sptech.sistema_estoque.repository.AlmoxarifadoRepository;
import school.sptech.sistema_estoque.repository.CategoriaRepository;
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
    private final SistemaMapper mapper;
    public MaterialService(MaterialRepository matrepository, CategoriaRepository catrepository, AlmoxarifadoRepository almrepository, UnidadeMedidaRepository unirepository, SistemaMapper mapper) {
        this.matrepository = matrepository;
        this.catrepository = catrepository;
        this.almrepository = almrepository;
        this.unirepository = unirepository;
        this.mapper = mapper;
    }

    public MaterialResponse cadastrarMaterial(MaterialRequest request){
        if (request==null){throw new InvalidMaterialRequestException("Material Inválido");} // VALIDAÇÃO INICIAL

        // VALIDAÇÕES OPTIONAL -- POSSÍVEL DE OTIMIZAR
        Optional<Categoria> catOpt = catrepository.findById(request.idCategoria());
        if (catOpt.isEmpty()){throw new CategoriaNaoExisteException("Categoria Não Encontrada");}
        Optional<Almoxarifado> estOpt = almrepository.findById(request.idEstoque());
        if (estOpt.isEmpty()){throw new EstoqueNaoExisteException("Estoque Não Encontrada");}
        Optional<UnidadeMedida> uniOpt = unirepository.findById(request.idUnidadeMedida());
        if (uniOpt.isEmpty()){throw new UnidadeMedidaNaoExisteException("Unidade de Medida Não Encontrada");}

        // CONVERSÃO OPTIONAL - ENTIDADE CATEGORIA, ESTOQUE E UNIDADE DE MEDIDA
        Categoria c = catOpt.get();
        Almoxarifado a = estOpt.get();
        UnidadeMedida u = uniOpt.get();
        Material m = mapper.toMaterialEntity(request,c,a,u); // CONVERSÃO REQUEST - ENTIDADE MATERIAL
        matrepository.save(m);
        return mapper.toMaterialResponse(m);
    }
    public List<MaterialResponse> listarMateriais(){
        // CONVERTENDO ENTIDADE - RESPONSE MATERIAL + EXIBIR
        return matrepository.findAll().stream()
                .map(mapper::toMaterialResponse)
                .toList();
    }
    public void excluirMaterial(Integer id){
        Optional<Material> opt = matrepository.findById(id);
        if (opt.isEmpty()){throw new MaterialNaoExisteException("Material Não Encontrado");}
        matrepository.delete(opt.get());
    }
}
