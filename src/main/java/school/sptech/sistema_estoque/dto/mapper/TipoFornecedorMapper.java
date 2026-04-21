package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.tipo_fornecedor.TipoFornecedorResponse;
import school.sptech.sistema_estoque.model.estoque.TipoFornecedor;

public class TipoFornecedorMapper {

    public static TipoFornecedorResponse toTipoFornecedorResponse(TipoFornecedor entity){
        TipoFornecedorResponse tipoFornecedorResponse = new TipoFornecedorResponse(entity.getId(), entity.getNomeTipo());
        return tipoFornecedorResponse;
    }

    public static TipoFornecedor toTipoFornecedorEntity(TipoFornecedorResponse response){
        TipoFornecedor tipoFornecedorEntity = new TipoFornecedor(response.id(), response.nomeTipo());
        return tipoFornecedorEntity;
    }

}
