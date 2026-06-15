package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.exception.EntidadeConflictException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.port.AlmoxarifadoPort;
import java.util.List;

@Service
@AllArgsConstructor
public class AlmoxarifadoService {
    private final AlmoxarifadoPort almoxarifadoPort;

    public Almoxarifado cadastrarAlmoxarifado(AlmoxarifadoRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Almoxarifado invalido");}
        if (almoxarifadoPort.findByNumeroSala(request.numeroSala()).isPresent()) {throw new EntidadeConflictException("Almoxarifado com esse numero de sala já existe");}
        Almoxarifado almoxarifado = new Almoxarifado(); almoxarifado.setNumeroSala(request.numeroSala());
        return almoxarifadoPort.save(almoxarifado);
    }

    public List<Almoxarifado> listarAlmoxarifados() {
        return almoxarifadoPort.findAll();
    }

    public void excluirAlmoxarifado(Integer id){
        Almoxarifado almoxarifado = almoxarifadoPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Almoxarifado Não Encontrado"));
        almoxarifadoPort.delete(almoxarifado);
    }
}