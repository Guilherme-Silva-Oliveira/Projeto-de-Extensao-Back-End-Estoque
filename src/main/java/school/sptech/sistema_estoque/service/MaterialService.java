package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.dashboard.MaterialMaisSolicitadoDto;
import school.sptech.sistema_estoque.dto.estoque.dashboard.MaterialProximoMinimoDto;
import school.sptech.sistema_estoque.dto.estoque.dashboard.MovimentacaoMaterialDto;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialUpdateRequest;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialRequest;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialResponse;
import school.sptech.sistema_estoque.dto.mapper.MaterialMapper;
import school.sptech.sistema_estoque.exception.EntidadeConflictException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.*;
import school.sptech.sistema_estoque.port.*;
import school.sptech.sistema_estoque.repository.LimiteRepository;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.sptech.sistema_estoque.repository.ListaMaterialRepository;
import school.sptech.sistema_estoque.repository.MaterialQuantidadeRepository;
import school.sptech.sistema_estoque.repository.PedidoEntradaRepository;

@Service
@AllArgsConstructor
public class MaterialService {
    private static final double MARGEM_PROXIMO = 0.2; // 20% acima do mínimo já conta como "próximo"

    private final MaterialPort materialPort;
    private final CategoriaPort categoriaPort;
    private final AlmoxarifadoPort almoxarifadoPort;
    private final UnidadeMedidaPort unidadeMedidaPort;
    private final SetorEstoquePort setorEstoquePort;
    private final LimiteRepository limiteRepository;
    private final PedidoEntradaRepository pedidoEntradaRepository;
    private final ListaMaterialRepository listaMaterialRepository;

    public Material cadastrarMaterial(MaterialRequest request){
        if (request==null){throw new EntidadeInvalidException("Material Inválido");}
        if (materialPort.existsByNomeMaterialAndAlmoxarifadoId(request.nomeMaterial(), request.idAlmoxarifado())){
            throw new EntidadeConflictException("Já existe um Material cadastrado com esse email e id de material");
        }

        Categoria categoria = categoriaPort.findById(request.idCategoria()).orElseThrow(()-> new EntidadeNaoExisteException("Categoria Não Encontrado"));
        Almoxarifado almoxarifado = almoxarifadoPort.findById(request.idAlmoxarifado()).orElseThrow(()-> new EntidadeNaoExisteException("Almoxarifado Não Encontrado"));
        UnidadeMedida unidadeMedida = unidadeMedidaPort.findById(request.idUnidadeMedida()).orElseThrow(()-> new EntidadeNaoExisteException("Unidade de Medida Não Encontrado"));
        SetorEstoque setorEstoque = setorEstoquePort.findById(request.setorId()).orElseThrow(()-> new EntidadeNaoExisteException("Setor de Estoque Não Encontrado"));

        Material m = new Material();
        m.setNomeMaterial(request.nomeMaterial());
        m.setUnidadeMedida(unidadeMedida);
        m.setCategoria(categoria);
        m.setAlmoxarifado(almoxarifado);
        m.setQuantidade(0);
        m.setDescricao(request.descricao());
        m.setSetorEstoque(setorEstoque);

        Material salvo = materialPort.save(m);
        return salvo;
    }

    public Page<Material> listarMateriais(Pageable pageable){
        return materialPort.findAll(pageable);
    }

    public void excluirMaterial(Integer id){
        Material material = materialPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Material Não Encontrado"));
        materialPort.delete(material);
    }

    public MaterialResponse atualizarParcial(Integer id, MaterialUpdateRequest request) {
        Material material = materialPort.findById(id).orElseThrow(() -> new EntidadeInvalidException("Material não encontrado"));
        if (request.nomeMaterial() != null) {material.setNomeMaterial(request.nomeMaterial());}
        if (request.quantidade() != null) {material.setQuantidade(request.quantidade());}
        if (request.descricao() != null) {material.setDescricao(request.descricao());}
        Material salvo = materialPort.save(material);
        return MaterialMapper.toResponse(salvo);
    }

    public MaterialMaisSolicitadoDto buscarMaterialMaisSolicitado(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (dataFim == null) {
            dataFim = LocalDateTime.now();
        }
        if (dataInicio == null) {
            dataInicio = dataFim.minusDays(30);
        }

        List<MaterialMaisSolicitadoDto> resultados = materialPort.findMaterialMaisSolicitadoPorPeriodo(dataInicio, dataFim);

        if (resultados.isEmpty()) {
            return new MaterialMaisSolicitadoDto("Nenhum material no período", 0L, dataInicio, dataFim);
        }

        MaterialMaisSolicitadoDto resultadoBanco = resultados.get(0);

        return new MaterialMaisSolicitadoDto(
                resultadoBanco.nomeMaterial(),
                resultadoBanco.totalSolicitado(),
                dataInicio,
                dataFim
        );
    }

    public List<MaterialProximoMinimoDto> buscarMateriaisProximosOuAbaixoDoMinimo() {

        List<Limite> limitesMinimos = limiteRepository.findByDescLimite("MINIMO");
        List<MaterialProximoMinimoDto> resultado = new ArrayList<>();

        for (Limite limite : limitesMinimos) {
            Material material = limite.getMaterial();
            if (material == null || limite.getLimite() == null) {
                continue;
            }

            int minimo = limite.getLimite().intValue();
            int atual = material.getQuantidade();
            int diferenca = atual - minimo;
            double margem = minimo * MARGEM_PROXIMO;

            boolean proximoOuAbaixo = diferenca <= margem;

            if (proximoOuAbaixo) {
                resultado.add(new MaterialProximoMinimoDto(
                        material.getNomeMaterial(),
                        atual,
                        minimo,
                        diferenca
                ));
            }
        }

        resultado.sort(Comparator.comparingInt(MaterialProximoMinimoDto::diferenca));
        return resultado;
    }

    public List<MovimentacaoMaterialDto> buscarTop10Movimentacoes() {
        Map<String, Long> entradasPorMaterial = new HashMap<>();
        for (MaterialQuantidadeRepository p : pedidoEntradaRepository.somarEntradasPorMaterial()) {
            entradasPorMaterial.put(p.getNomeMaterial(), p.getTotal());
        }

        Map<String, Long> saidasPorMaterial = new HashMap<>();
        for (MaterialQuantidadeRepository p : listaMaterialRepository.somarSaidasPorMaterial()) {
            saidasPorMaterial.put(p.getNomeMaterial(), p.getTotal());
        }

        Set<String> todosOsMateriais = new HashSet<>();
        todosOsMateriais.addAll(entradasPorMaterial.keySet());
        todosOsMateriais.addAll(saidasPorMaterial.keySet());

        List<MovimentacaoMaterialDto> resultado = new ArrayList<>();
        for (String nome : todosOsMateriais) {
            long entradas = entradasPorMaterial.getOrDefault(nome, 0L);
            long saidas = saidasPorMaterial.getOrDefault(nome, 0L);
            resultado.add(new MovimentacaoMaterialDto(nome, entradas, saidas));
        }

        resultado.sort((a, b) -> Long.compare(b.entradas() + b.saidas(), a.entradas() + a.saidas()));

        return resultado.size() > 10 ? resultado.subList(0, 10) : resultado;
    }
}