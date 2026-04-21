package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.exception.InvalidAlmoxarifadoRequestException;
import school.sptech.sistema_estoque.exception.InvalidLimiteRequestException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.port.AlmoxarifadoPort;
import school.sptech.sistema_estoque.repository.LimiteRepository;

import java.util.List;

@Service
public class AlmoxarifadoService {
    private final LimiteRepository limrepository;
    private final AlmoxarifadoPort almoxarifadoPort;
    public AlmoxarifadoService(LimiteRepository limrepository, AlmoxarifadoPort almoxarifadoPort) {
        this.limrepository = limrepository;
        this.almoxarifadoPort = almoxarifadoPort;
    }

    public Almoxarifado cadastrarAlmoxarifado(AlmoxarifadoRequest request) {
        if (request == null) {
            throw new InvalidAlmoxarifadoRequestException("Almoxarifado invalido");
        }

        if (request.idsLimites() == null || request.idsLimites().isEmpty()) {
            throw new InvalidLimiteRequestException("Limites nao informados");
        }

        List<Limite> limites = limrepository.findAllById(request.idsLimites());
        if (limites.size() != request.idsLimites().size()) {
            throw new InvalidLimiteRequestException("Limite nao encontrado");
        }

        Almoxarifado almoxarifado = new Almoxarifado(null, request.numeroSala(), limites);
        return almoxarifadoPort.save(almoxarifado);
    }

    public List<Almoxarifado> listarAlmoxarifados() {
        return almoxarifadoPort.findAll();
    }
}
