package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.*;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.exception.*;
import school.sptech.sistema_estoque.model.estoque.*;
import school.sptech.sistema_estoque.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class SistemaService {
    private final CategoriaRepository catrepository;
    private final MaterialRepository matrepository;
    private final EstoqueRepository estrepository;
    private final UnidadeMedidaRepository unirepository;

    private final SistemaMapper mapper;
    private final ProfessorRepository professorRepository;

    public SistemaService(CategoriaRepository catrepository, MaterialRepository matrepository, EstoqueRepository estrepository, UnidadeMedidaRepository unirepository, SistemaMapper mapper, ProfessorRepository professorRepository) {
        this.catrepository = catrepository;
        this.matrepository = matrepository;
        this.estrepository = estrepository;
        this.unirepository = unirepository;
        this.mapper = mapper;
        this.professorRepository = professorRepository;
    }

    // -------------- CATEGORIA --------------
    public CategoriaResponse cadastrarCategoria(CategoriaRequest request){
        if (request==null){throw new InvalidCategoriaRequestException("Categoria Inválida");} // VALIDAÇÃO INICIAL
        Categoria c = mapper.toCategoriaEntity(request); // CONVERSÃO REQUEST - ENTIDADE CATEGORIA
        Categoria salvo = catrepository.save(c);
        return mapper.toCategoriaResponse(salvo);
    }
    public List<CategoriaResponse> listarCategorias(){
        // CONVERTENDO ENTIDADE - RESPONSE CATEGORIA + EXIBIR
        return catrepository.findAll().stream()
                .map(mapper::toCategoriaResponse)
                .toList();
    }
    public void excluirCategoria(Integer id){
        Optional<Categoria> opt = catrepository.findById(id);
        if (opt.isEmpty()){throw new CategoriaNaoExisteException("Categoria Não Encontrada");}
        catrepository.delete(opt.get());
    }
    // -------------- FIM CATEGORIA --------------

    // -------------- MATERIAL --------------
    public MaterialResponse cadastrarMaterial(MaterialRequest request){
        if (request==null){throw new InvalidMaterialRequestException("Material Inválido");} // VALIDAÇÃO INICIAL

        // VALIDAÇÕES OPTIONAL -- POSSÍVEL DE OTIMIZAR
        Optional<Categoria> catOpt = catrepository.findById(request.idCategoria());
        if (catOpt.isEmpty()){throw new CategoriaNaoExisteException("Categoria Não Encontrada");}
        Optional<Estoque> estOpt = estrepository.findById(request.idEstoque());
        if (estOpt.isEmpty()){throw new EstoqueNaoExisteException("Estoque Não Encontrada");}
        Optional<UnidadeMedida> uniOpt = unirepository.findById(request.idUnidadeMedida());
        if (uniOpt.isEmpty()){throw new UnidadeMedidaNaoExisteException("Categoria Não Encontrada");}

        // CONVERSÃO OPTIONAL - ENTIDADE CATEGORIA, ESTOQUE E UNIDADE DE MEDIDA
        Categoria c = catOpt.get();
        Estoque e = estOpt.get();
        UnidadeMedida u = uniOpt.get();
        Material m = mapper.toMaterialEntity(request,c,e,u); // CONVERSÃO REQUEST - ENTIDADE MATERIAL
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
    // -------------- FIM MATERIAL --------------

    // -------------- UNIDADE MEDIDA --------------
    public UnidadeMedidaResponse cadastrarUnidadeMedida(UnidadeMedidaRequest request){
        if (request==null){throw new InvalidUnidadeMedidaException("Unidade de Medida Inválido");} // VALIDAÇÃO INICIAL
        UnidadeMedida unidade = mapper.toUnidadeMedidaEntity(request);
        unirepository.save(unidade);
        return mapper.toUnidadeMedidaResponse(unidade);
    }
    public List<UnidadeMedidaResponse> listarUnidadeMedida(){
        return unirepository.findAll().stream()
                .map(mapper::toUnidadeMedidaResponse)
                .toList();
    }
    public void excluirUnidadeMedida(Integer id){
        Optional<UnidadeMedida> opt = unirepository.findById(id);
        if (opt.isEmpty()){throw new UnidadeMedidaNaoExisteException("Material Não Encontrado");}
        unirepository.delete(opt.get());
    }
    // -------------- FIM UNIDADE MEDIDA --------------

    // -------------- PROFESSOR --------------
    // CRUD PARA PROFESSOR
    public ProfessorResponse cadastrarProfessor(ProfessorRequest request){
        if (request == null){
            throw new InvalidProfessorRequestException("Professor Inválido");
        }

        Professor professor = mapper.toProfessorEntity(request);
        professorRepository.save(professor);
        return mapper.toProfessorResponse(professor);
    }

    public List<ProfessorResponse> listarProfessor(){
        return professorRepository.findAll().stream()
                .map(mapper::toProfessorResponse)
                .toList();
    }

    public void excluirProfessor(Integer id){
        Optional<Professor> opt = professorRepository.findById(id);
        if (opt.isEmpty()){
            throw new InvalidProfessorRequestException("Professor Não Encontrada");
        }
        professorRepository.delete(opt.get());
    }
    // -------------- FIM PROFESSOR --------------

    // -------------- SOLICITAÇÃO --------------
    // CRUD PARA SOLICITAÇÃO
    // -------------- FIM SOLICITAÇÃO --------------

    // -------------- ESCALA --------------
    // CRUD PARA ESCALA
    // -------------- FIM ESCALA --------------

    // -------------- LIMITE + TIPO_LIMITE --------------
    // CRUD PARA LIMITE
    // -------------- FIM LIMITE + TIPO_LIMITE --------------

    // -------------- FORNECEDOR + TIPO_FORNECEDOR --------------
    // CRUD FORNECEDOR
    // -------------- FIM FORNECEDOR + TIPO_FORNECEDOR --------------

    // -------------- PEDIDO_SAIDA + PEDIDO ENTRADA --------------
    // CRUD PEDIDO_SAIDA + PEDIDO_ENTRADA
    // -------------- FIM PEDIDO_SAIDA + PEDIDO_ENTRADA --------------
}
