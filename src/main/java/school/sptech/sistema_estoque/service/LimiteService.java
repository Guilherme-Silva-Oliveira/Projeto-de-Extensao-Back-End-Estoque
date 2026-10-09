package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.limite.LimitePatchDto;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.port.LimitePort;
import school.sptech.sistema_estoque.port.MaterialPort;

import java.util.List;

@Service
@AllArgsConstructor
public class LimiteService {
    private final LimitePort limitePort;
    private final MaterialPort materialPort;

    public Limite cadastrarLimite(LimiteRequest request){
        if (request == null){ throw new EntidadeInvalidException("Limite Inválido"); }
        Material material = materialPort.findById(request.idMaterial()).orElseThrow(()-> new EntidadeNaoExisteException("Material Não Encontrado"));
        Limite l = new Limite(null, request.descLimite(), request.limite(), material);
        return limitePort.save(l);
    }

    public List<Limite> listarLimites(){
        return limitePort.findAll();
    }

    public void excluirLimite(Integer id){
        Limite limite = limitePort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Limite Não Encontrado"));
        limitePort.delete(limite);
    }

    public Limite atualizarLimite(Integer id, LimitePatchDto dto){
        Limite limite = limitePort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Limite Não Encontrado"));
        return limitePort.save(limite);
    }
}