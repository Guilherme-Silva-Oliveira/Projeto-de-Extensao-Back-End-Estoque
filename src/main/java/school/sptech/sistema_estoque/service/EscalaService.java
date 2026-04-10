package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.EscalaRequest;
import school.sptech.sistema_estoque.dto.estoque.EscalaResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.exception.InvalidEscalaRequestException;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.repository.EscalaRepository;

import java.util.List;

@Service
public class EscalaService {
    private final EscalaRepository repository;
    private final SistemaMapper mapper;
    public EscalaService(EscalaRepository repository, SistemaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public EscalaResponse cadastrarEscala(EscalaRequest request){
        if (request == null){ throw new InvalidEscalaRequestException("Escala Inválida"); } // VALIDAÇÃO INICIAL

        Escala e = mapper.toEscalaEntity(request); // CONVERSÃO REQUEST - ENTIDADE ESCALA
        Escala salvo = repository.save(e);

        return mapper.toEscalaResponse(salvo);
    }

    public List<EscalaResponse> listarEscala(){
        // CONVERTENDO ENTIDADE - RESPONSE ESCALA + EXIBIR
        return repository.findAll().stream()
                .map(mapper::toEscalaResponse)
                .toList();
    }
}
