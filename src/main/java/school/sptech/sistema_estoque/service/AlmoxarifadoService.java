package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.dto.estoque.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.exception.InvalidAlmoxarifadoRequestException;
import school.sptech.sistema_estoque.exception.InvalidLimiteRequestException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.repository.AlmoxarifadoRepository;
import school.sptech.sistema_estoque.repository.LimiteRepository;

import java.util.List;

@Service
public class AlmoxarifadoService {
    private final LimiteRepository limrepository;
    private final AlmoxarifadoRepository almrepository;
    private final SistemaMapper mapper;
    public AlmoxarifadoService(LimiteRepository limrepository, AlmoxarifadoRepository almrepository, SistemaMapper mapper) {
        this.limrepository = limrepository;
        this.almrepository = almrepository;
        this.mapper = mapper;
    }

    public AlmoxarifadoResponse cadastrarAlmoxarifado(AlmoxarifadoRequest request) {
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

        Almoxarifado almoxarifado = mapper.toAlmoxarifadoEntity(request, limites);
        Almoxarifado salvo = almrepository.save(almoxarifado);
        return mapper.toAlmoxarifadoResponse(salvo);
    }

    public List<AlmoxarifadoResponse> listarAlmoxarifados() {
        return almrepository.findAll().stream()
                .map(mapper::toAlmoxarifadoResponse)
                .toList();
    }
}
