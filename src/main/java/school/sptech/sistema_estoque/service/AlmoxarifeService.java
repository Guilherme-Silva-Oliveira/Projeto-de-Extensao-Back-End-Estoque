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
import school.sptech.sistema_estoque.exception.EntidadeConflictException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
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

    private final AuthenticationManager authenticationManager;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    private final PasswordEncoder encoder;

    public AlmoxarifeService(AlmoxarifadoRepository almoxarifadoRepository, AlmoxarifeRepository almoxarifeRepository, AuthenticationManager authenticationManager, GerenciadorTokenJwt gerenciadorTokenJwt, PasswordEncoder encoder) {
        this.almoxarifadoRepository = almoxarifadoRepository;
        this.almoxarifeRepository = almoxarifeRepository;
        this.authenticationManager = authenticationManager;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.encoder = encoder;
    }

    public Almoxarife cadastrarAlmoxarife(AlmoxarifeRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Almoxarife invalido");}
        if (almoxarifeRepository.existsByEmailAndAlmoxarifadoId(request.email(), request.idAlmoxarifado())){throw new EntidadeConflictException("Já existe um almoxarife cadastrado com esse email e id de almoxarifado");}
        Optional<Almoxarifado> almoxarifadoOptional = almoxarifadoRepository.findById(request.idAlmoxarifado());
        if (almoxarifadoOptional.isEmpty()) {throw new EntidadeInvalidException("Almoxarifado nao encontrado");}
        String novaSenha = encoder.encode(request.senha());
        Almoxarife almoxarife = new Almoxarife(null, request.nome(), request.email(), request.telefone(), novaSenha, almoxarifadoOptional.get());
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


    public AlmoxarifeToken autenticar(Almoxarife almoxarife) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                almoxarife.getEmail(), almoxarife.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Almoxarife almoxarifeAutenticado =
            almoxarifeRepository.findByEmail(almoxarife.getEmail())
                    .orElseThrow(
                            () -> new ResponseStatusException(404, "Email do Almoxarife não cadastrado", null));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return AlmoxarifeMapper.toEntity(almoxarifeAutenticado, token);
    }
}
