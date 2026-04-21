package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.repository.AlmoxarifadoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlmoxarifadoService {
    private final AlmoxarifadoRepository almrepository;

    public AlmoxarifadoService(AlmoxarifadoRepository almrepository) {
        this.almrepository = almrepository;
    }

    public Almoxarifado cadastrarAlmoxarifado(AlmoxarifadoRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Almoxarifado invalido");}
        Almoxarifado almoxarifado = new Almoxarifado(null, request.numeroSala());
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
