package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.EscalaRequest;
import school.sptech.sistema_estoque.exception.InvalidEscalaRequestException;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.repository.EscalaRepository;

import java.util.List;

@Service
public class EscalaService {
    private final EscalaRepository repository;
    public EscalaService(EscalaRepository repository) {
        this.repository = repository;
    }

    public Escala cadastrarEscala(EscalaRequest request){
        if (request == null){ throw new InvalidEscalaRequestException("Escala Inválida"); } // VALIDAÇÃO INICIAL

        Escala e = new Escala(null, request.nomeEscala()); // CONVERSÃO REQUEST - ENTIDADE ESCALA
        return repository.save(e);
    }

    public List<Escala> listarEscala(){
        // RETORNANDO ENTIDADES ESCALA
        return repository.findAll();
    }
}
