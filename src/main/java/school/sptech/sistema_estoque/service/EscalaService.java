package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.escala.EscalaRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.port.EscalaPort;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EscalaService {
    private final EscalaPort escalaPort;

    public Escala cadastrarEscala(EscalaRequest request){
        if (request == null){ throw new EntidadeInvalidException("Escala Inválida"); }
        if (escalaPort.findByNomeEscala(request.nomeEscala()).isPresent()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Escala com esse nome já existe"); }
        Escala e = new Escala(null, request.nomeEscala());
        return escalaPort.save(e);
    }

    public List<Escala> listarEscala(){
        return escalaPort.findAll();
    }

    public void excluirEscala(Integer id){
        Escala escala = escalaPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Escala Não Encontrada"));
        escalaPort.delete(escala);
    }
}


