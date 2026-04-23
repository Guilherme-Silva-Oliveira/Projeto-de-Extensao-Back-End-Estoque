package school.sptech.sistema_estoque.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.port.AlmoxarifadoPort;
import school.sptech.sistema_estoque.repository.LimiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlmoxarifadoService {
    private final LimiteRepository limrepository;
    private final AlmoxarifadoPort almoxarifadoPort;
    public AlmoxarifadoService(LimiteRepository limrepository, AlmoxarifadoPort almoxarifadoPort) {
        this.limrepository = limrepository;
        this.almoxarifadoPort = almoxarifadoPort;
    }

    public Almoxarifado cadastrarAlmoxarifado(AlmoxarifadoRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Almoxarifado invalido");}
        if (!almrepository.findByNumeroSala(request.numeroSala()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Almoxarifado com esse numero de sala já existe");
        }
        Almoxarifado almoxarifado = new Almoxarifado(null, request.numeroSala());
        return almrepository.save(almoxarifado);
    }

    public List<Almoxarifado> listarAlmoxarifados() {
        return almoxarifadoPort.findAll();
    }

    public void excluirAlmoxarifado(Integer id){
        Optional<Almoxarifado> opt = almrepository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Almoxarifado Não Encontrado");}
        almrepository.delete(opt.get());
    }
}
