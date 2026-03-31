package school.sptech.sistema_estoque.dto.mapper;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.dto.estoque.*;
import school.sptech.sistema_estoque.model.estoque.*;

@Component
public class SistemaMapper {
    // CATEGORIA
    public Categoria toCategoriaEntity(CategoriaRequest request){
        Categoria c = new Categoria();
        c.setNomeCategoria(request.nomeCategoria());
        return c;
    }
    public CategoriaResponse toCategoriaResponse(Categoria entity){
        return new CategoriaResponse(entity.getId(),entity.getNomeCategoria());
    }

    // MATERIAL
    public Material toMaterialEntity(MaterialRequest request, Categoria categoria, Estoque estoque, UnidadeMedida unidadeMedida){
        Material m = new Material();
        m.setCategoria(categoria);
        m.setEstoque(estoque);
        m.setUnidadeMedida(unidadeMedida);
        return m;
    }
    public MaterialResponse toMaterialResponse(Material entity){
        return new MaterialResponse(entity.getId(),entity.getCategoria());
    }

    // UNIDADE DE MEDIDA
    public UnidadeMedida toUnidadeMedidaEntity(UnidadeMedidaRequest request){
        UnidadeMedida u = new UnidadeMedida();
        u.setNomeUnidade(request.nomeUnidade());
        return u;
    }
    public UnidadeMedidaResponse toUnidadeMedidaResponse(UnidadeMedida entity){
        return new UnidadeMedidaResponse(entity.getId(),entity.getNomeUnidade());
    }

    public Professor toProfessorEntity(ProfessorRequest professorRequest){
        Professor entity = new Professor();
        entity.setEmail(professorRequest.email());
        entity.setNome(professorRequest.nome());
        entity.setTelefone(professorRequest.telefone());
        return entity;
    }

    public ProfessorResponse toProfessorResponse(Professor entity){
        return new ProfessorResponse(entity.getId(), entity.getNome(), entity.getEmail(), entity.getTelefone());
    }


}
