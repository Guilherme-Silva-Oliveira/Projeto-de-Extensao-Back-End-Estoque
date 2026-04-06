package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.AlmoxarifeRequest;
import school.sptech.sistema_estoque.dto.estoque.AlmoxarifeResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
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
    private final SistemaMapper mapper;
    public AlmoxarifeService(AlmoxarifadoRepository almoxarifadoRepository, AlmoxarifeRepository almoxarifeRepository, SistemaMapper mapper) {
        this.almoxarifadoRepository = almoxarifadoRepository;
        this.almoxarifeRepository = almoxarifeRepository;
        this.mapper = mapper;
    }

    public AlmoxarifeResponse cadastrarAlmoxarife(AlmoxarifeRequest request) {
        if (request == null) {
            throw new InvalidAlmoxarifeRequestException("Almoxarife invalido");
        }

        Optional<Almoxarifado> almoxarifadoOptional = almoxarifadoRepository.findById(request.idAlmoxarifado());
        if (almoxarifadoOptional.isEmpty()) {
            throw new InvalidAlmoxarifadoRequestException("Almoxarifado nao encontrado");
        }

        Almoxarife almoxarife = mapper.toAlmoxarifeEntity(request, almoxarifadoOptional.get());
        Almoxarife salvo = almoxarifeRepository.save(almoxarife);
        return mapper.toAlmoxarifeResponse(salvo);
    }

    public List<AlmoxarifeResponse> listarAlmoxarifes() {
        return almoxarifeRepository.findAll().stream()
                .map(mapper::toAlmoxarifeResponse)
                .toList();
    }
}
