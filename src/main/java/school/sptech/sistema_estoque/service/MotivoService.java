package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.motivo.MotivoRequest;
import school.sptech.sistema_estoque.dto.mapper.MotivoMapper;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Motivo;
import school.sptech.sistema_estoque.port.MotivoPort;

import java.util.List;
import java.util.Optional;

@Service
public class MotivoService {
    private final MotivoPort motivoPort;

    public MotivoService(MotivoPort motivoPort) {
        this.motivoPort = motivoPort;
    }

    public Motivo cadastrarMotivo(MotivoRequest request){
        if (request == null){throw new EntidadeInvalidException("Motivo Inválido");}
        Motivo motivo = MotivoMapper.toEntity(request);
        return motivoPort.save(motivo);
    }

    public List<Motivo> listarMotivos(){
        return motivoPort.findAll();
    }

    public void excluirMotivo(Integer id){
        Optional<Motivo> motivoOptional = motivoPort.findById(id);
        if (motivoOptional.isEmpty()){throw new EntidadeNaoExisteException("Motivo Não Encontrado");}
        motivoPort.delete(motivoOptional.get());
    }
}
