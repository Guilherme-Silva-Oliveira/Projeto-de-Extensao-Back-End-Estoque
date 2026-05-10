package school.sptech.sistema_estoque.service;

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
public class EscalaService {
    private final EscalaPort escalaPort;

    public EscalaService(EscalaPort escalaPort) {
        this.escalaPort = escalaPort;
    }

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
        Optional<Escala> opt = escalaPort.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Escala Não Encontrada");}
        escalaPort.delete(opt.get());
    }
}


