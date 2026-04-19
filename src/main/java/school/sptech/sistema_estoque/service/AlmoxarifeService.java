package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeRequest;
import school.sptech.sistema_estoque.exception.AlmoxarifadoNaoExisteException;
import school.sptech.sistema_estoque.exception.AlmoxarifeNaoExisteException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.exception.InvalidAlmoxarifadoRequestException;
import school.sptech.sistema_estoque.exception.InvalidAlmoxarifeRequestException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Almoxarife;
import school.sptech.sistema_estoque.repository.AlmoxarifadoRepository;
import school.sptech.sistema_estoque.repository.AlmoxarifeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlmoxarifeService {
    private final AlmoxarifadoRepository almoxarifadoRepository;
    private final AlmoxarifeRepository almoxarifeRepository;
    public AlmoxarifeService(AlmoxarifadoRepository almoxarifadoRepository, AlmoxarifeRepository almoxarifeRepository) {
        this.almoxarifadoRepository = almoxarifadoRepository;
        this.almoxarifeRepository = almoxarifeRepository;
    }

    public Almoxarife cadastrarAlmoxarife(AlmoxarifeRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Almoxarife invalido");}
        Optional<Almoxarifado> almoxarifadoOptional = almoxarifadoRepository.findById(request.idAlmoxarifado());
        if (almoxarifadoOptional.isEmpty()) {throw new EntidadeInvalidException("Almoxarifado nao encontrado");}

        Almoxarife almoxarife = new Almoxarife(null, request.nome(), request.email(), request.telefone(), request.senha(), almoxarifadoOptional.get());
        return almoxarifeRepository.save(almoxarife);
    }

    public List<Almoxarife> listarAlmoxarifes() {
        return almoxarifeRepository.findAll();
    }

    public void excluirAlmoxarife(Integer id){
        Optional<Almoxarife> opt = almoxarifeRepository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Almoxarife Não Encontrado");}
        almoxarifeRepository.delete(opt.get());
    }
}
