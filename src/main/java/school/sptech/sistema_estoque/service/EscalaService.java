package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.escala.EscalaRequest;
import school.sptech.sistema_estoque.exception.AlmoxarifadoNaoExisteException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.exception.EscalaNaoExisteException;
import school.sptech.sistema_estoque.exception.InvalidEscalaRequestException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.repository.EscalaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EscalaService {
    private final EscalaRepository repository;
    public EscalaService(EscalaRepository repository) {
        this.repository = repository;
    }

    public Escala cadastrarEscala(EscalaRequest request){
        if (request == null){ throw new EntidadeInvalidException("Escala Inválida"); }
        Escala e = new Escala(null, request.nomeEscala());
        return repository.save(e);
    }

    public List<Escala> listarEscala(){
        return repository.findAll();
    }

    public void excluirEscala(Integer id){
        Optional<Escala> opt = repository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Escala Não Encontrada");}
        repository.delete(opt.get());
    }
}
