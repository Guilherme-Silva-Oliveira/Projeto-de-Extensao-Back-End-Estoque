package school.sptech.sistema_estoque.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.sistema_estoque.config.GerenciadorTokenJwt;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeToken;
import school.sptech.sistema_estoque.dto.mapper.AlmoxarifeMapper;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Almoxarife;
import school.sptech.sistema_estoque.port.AlmoxarifadoPort;
import school.sptech.sistema_estoque.port.AlmoxarifePort;

import java.util.List;
import java.util.Optional;

@Service
public class AlmoxarifeService {
    private final AlmoxarifadoPort almoxarifadoPort;
    private final AlmoxarifePort almoxarifePort;

    private final AuthenticationManager authenticationManager;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    private final PasswordEncoder encoder;

    public AlmoxarifeService(AlmoxarifadoPort almoxarifadoPort, AlmoxarifePort almoxarifePort, AuthenticationManager authenticationManager, GerenciadorTokenJwt gerenciadorTokenJwt, PasswordEncoder encoder) {
        this.almoxarifadoPort = almoxarifadoPort;
        this.almoxarifePort = almoxarifePort;
        this.authenticationManager = authenticationManager;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.encoder = encoder;
    }

    public Almoxarife cadastrarAlmoxarife(AlmoxarifeRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Almoxarife invalido");}
        if (almoxarifePort.existsByEmailAndAlmoxarifadoId(request.email(), request.idAlmoxarifado())){throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um almoxarife cadastrado com esse email e id de almoxarifado");}
        Optional<Almoxarifado> almoxarifadoOptional = almoxarifadoPort.findById(request.idAlmoxarifado());
        if (almoxarifadoOptional.isEmpty()) {throw new EntidadeInvalidException("Almoxarifado nao encontrado");}
        String novaSenha = encoder.encode(request.senha());
        Almoxarife almoxarife = new Almoxarife(null, request.nome(), request.email(), request.telefone(), novaSenha, almoxarifadoOptional.get());
        return almoxarifePort.save(almoxarife);
    }

    public List<Almoxarife> listarAlmoxarifes() {
        return almoxarifePort.findAll();
    }

    public void excluirAlmoxarife(Integer id){
        Optional<Almoxarife> opt = almoxarifePort.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Almoxarife Não Encontrado");}
        almoxarifePort.delete(opt.get());
    }


    public AlmoxarifeToken autenticar(Almoxarife almoxarife) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                almoxarife.getEmail(), almoxarife.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Almoxarife almoxarifeAutenticado =
                almoxarifePort.findByEmail(almoxarife.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do Almoxarife não cadastrado", null));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return AlmoxarifeMapper.toEntity(almoxarifeAutenticado, token);
    }

}
