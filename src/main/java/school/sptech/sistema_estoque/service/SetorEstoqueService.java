
package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.sistema_estoque.dto.estoque.categoria.CategoriaRequest;
import school.sptech.sistema_estoque.dto.estoque.setor_estoque.SetorEstoqueRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Categoria;
import school.sptech.sistema_estoque.model.estoque.SetorEstoque;
import school.sptech.sistema_estoque.port.AlmoxarifadoPort;
import school.sptech.sistema_estoque.port.CategoriaPort;
import school.sptech.sistema_estoque.port.SetorEstoquePort;

import java.util.List;

@Service
@AllArgsConstructor
public class SetorEstoqueService {
    private final SetorEstoquePort setorPort;
    private final AlmoxarifadoPort almoxarifadoPort;

    public SetorEstoque cadastrarSetorEstoque(SetorEstoqueRequest request){
        if (request == null) {throw new EntidadeInvalidException("Setor de Estoque Inválido");}
        Almoxarifado almoxarifado = almoxarifadoPort.findById(request.almoxarifadoId()).orElseThrow(() -> new EntidadeInvalidException("Almoxarifado Não Encontrado"));
        SetorEstoque s = new SetorEstoque();
        s.setAlmoxarifado(almoxarifado);
        s.setIdentificadorSetor(request.identificadorSetor());
        return setorPort.save(s);
    }

    public List<SetorEstoque> listarSetores(){
        return setorPort.findAll();
    }

    public void excluirSetor(Integer id){
        SetorEstoque setor = setorPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Setor de Estoque Não Encontrado"));
        setorPort.delete(setor);
    }
}
