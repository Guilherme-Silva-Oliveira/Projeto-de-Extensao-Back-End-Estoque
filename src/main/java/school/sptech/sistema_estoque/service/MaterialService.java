package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.dashboard.MaterialMaisSolicitadoDto;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialUpdateRequest;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialRequest;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialResponse;
import school.sptech.sistema_estoque.dto.mapper.MaterialMapper;
import school.sptech.sistema_estoque.exception.EntidadeConflictException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.*;
import school.sptech.sistema_estoque.port.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MaterialService {
    private final MaterialPort materialPort;;
    private final CategoriaPort categoriaPort;
    private final AlmoxarifadoPort almoxarifadoPort;
    private final UnidadeMedidaPort unidadeMedidaPort;

    public Material cadastrarMaterial(MaterialRequest request){
        if (request==null){throw new EntidadeInvalidException("Material Inválido");}
        if (materialPort.existsByNomeMaterialAndAlmoxarifadoId(request.nomeMaterial(), request.idAlmoxarifado())){
            throw new EntidadeConflictException("Já existe um Material cadastrado com esse email e id de material");
        }

        Categoria categoria = categoriaPort.findById(request.idCategoria()).orElseThrow(()-> new EntidadeNaoExisteException("Categoria Não Encontrado"));
        Almoxarifado almoxarifado = almoxarifadoPort.findById(request.idAlmoxarifado()).orElseThrow(()-> new EntidadeNaoExisteException("Almoxarifado Não Encontrado"));
        UnidadeMedida unidadeMedida = unidadeMedidaPort.findById(request.idUnidadeMedida()).orElseThrow(()-> new EntidadeNaoExisteException("Unidade de Medida Não Encontrado"));

        Material m = new Material();
        m.setNomeMaterial(request.nomeMaterial());
        m.setUnidadeMedida(unidadeMedida);
        m.setCategoria(categoria);
        m.setAlmoxarifado(almoxarifado);
        m.setQuantidade(0);
        m.setDescricao(request.descricao());
        Material salvo = materialPort.save(m);
        return salvo;
    }

    public List<Material> listarMateriais(){
        return materialPort.findAll();
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
}