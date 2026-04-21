package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorResponse;
import school.sptech.sistema_estoque.dto.estoque.tipo_fornecedor.TipoFornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.tipo_fornecedor.TipoFornecedorResponse;
import school.sptech.sistema_estoque.model.estoque.Fornecedor;
import school.sptech.sistema_estoque.model.estoque.TipoFornecedor;

public class FornecedorMapper {
    public static TipoFornecedor toTipoFornecedorEntity(TipoFornecedorRequest request) {
        TipoFornecedor tf = new TipoFornecedor();
        tf.setNomeTipo(request.nomeTipo());
        return tf;
    }

    public static TipoFornecedorResponse toTipoFornecedorResponse(TipoFornecedor entity) {
        return new TipoFornecedorResponse(entity.getId(), entity.getNomeTipo());
    }

    public static Fornecedor toFornecedorEntity(FornecedorRequest request, TipoFornecedor tipoFornecedor) {
        Fornecedor f = new Fornecedor();
        f.setNome(request.nome());
        f.setEmail(request.email());
        f.setTelefone(request.telefone());
        f.setTipoFornecedor(tipoFornecedor);
        return f;
    }

    public static FornecedorResponse toFornecedorResponse(Fornecedor entity) {
        if (entity == null){
            return null;
        }
        return new FornecedorResponse(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                TipoFornecedorMapper.toTipoFornecedorResponse(entity.getTipoFornecedor())
        );
    }
}
