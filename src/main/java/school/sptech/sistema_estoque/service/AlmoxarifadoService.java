package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.exception.AlmoxarifadoNaoExisteException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.exception.InvalidAlmoxarifadoRequestException;
import school.sptech.sistema_estoque.exception.InvalidLimiteRequestException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.repository.AlmoxarifadoRepository;
import school.sptech.sistema_estoque.repository.LimiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlmoxarifadoService {
    private final LimiteRepository limrepository;
    private final AlmoxarifadoRepository almrepository;
    public AlmoxarifadoService(LimiteRepository limrepository, AlmoxarifadoRepository almrepository) {
        this.limrepository = limrepository;
        this.almrepository = almrepository;
    }

    public Almoxarifado cadastrarAlmoxarifado(AlmoxarifadoRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Almoxarifado invalido");}
        if (request.idsLimites() == null || request.idsLimites().isEmpty()) {throw new EntidadeInvalidException("Limites nao informados");}

        List<Limite> limites = limrepository.findAllById(request.idsLimites());
        if (limites.size() != request.idsLimites().size()) {
            throw new EntidadeInvalidException("Limite nao encontrado");
        }

        Almoxarifado almoxarifado = new Almoxarifado(null, request.numeroSala(), limites);
        return almrepository.save(almoxarifado);
    }

    public List<Almoxarifado> listarAlmoxarifados() {
        return almrepository.findAll();
    }

    public void excluirAlmoxarifado(Integer id){
        Optional<Almoxarifado> opt = almrepository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Almoxarifado Não Encontrado");}
        almrepository.delete(opt.get());
    }
}
