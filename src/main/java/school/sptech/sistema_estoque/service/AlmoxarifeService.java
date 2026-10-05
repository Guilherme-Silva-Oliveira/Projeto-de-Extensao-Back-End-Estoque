package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import school.sptech.sistema_estoque.config.GerenciadorTokenJwt;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.*;
import school.sptech.sistema_estoque.dto.mapper.AlmoxarifeMapper;
import school.sptech.sistema_estoque.enums.Role;
import school.sptech.sistema_estoque.exception.EntidadeConflictException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.exception.UsuarioBloqueadoException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Almoxarife;
import school.sptech.sistema_estoque.port.AlmoxarifadoPort;
import school.sptech.sistema_estoque.port.AlmoxarifePort;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AlmoxarifeService {
    private final AlmoxarifadoPort almoxarifadoPort;
    private final AlmoxarifePort almoxarifePort;
    private final AuthenticationManager authenticationManager;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final PasswordEncoder encoder;
    private final LoginAttemptService loginAttemptService;

    public Almoxarife cadastrarAlmoxarife(AlmoxarifeRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Almoxarife invalido");}
        if (almoxarifePort.existsByEmailAndAlmoxarifadoId(request.email(), request.idAlmoxarifado())){throw new EntidadeConflictException("Já existe um almoxarife cadastrado com esse email e id de almoxarifado");}
        Almoxarifado almoxarifado = almoxarifadoPort.findById(request.idAlmoxarifado()).orElseThrow(()-> new EntidadeNaoExisteException("Almoxarifado Não Encontrado"));
        String novaSenha = encoder.encode(request.senha());
        Almoxarife almoxarife = new Almoxarife(null, request.nome(), request.email(), request.telefone(), LocalDateTime.now(), LocalDateTime.now(), true, novaSenha, Role.ALMOXARIFE, almoxarifado);
        return almoxarifePort.save(almoxarife);
    }

    public List<Almoxarife> listarAlmoxarifes() {
        return almoxarifePort.findAll();
    }

    public void excluirAlmoxarife(Integer id){
        Almoxarife almoxarife = almoxarifePort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Almoxarife Não Encontrado"));
        almoxarifePort.delete(almoxarife);
    }


    public AlmoxarifeToken autenticar(Almoxarife almoxarife) {
        if (loginAttemptService.isBlocked(almoxarife.getEmail())) {
            throw new UsuarioBloqueadoException("Conta temporariamente bloqueada por excesso de tentativas. Tente novamente mais tarde.");
        }

        final UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(almoxarife.getEmail(), almoxarife.getSenha());

        final Authentication authentication;
        try {
            authentication = this.authenticationManager.authenticate(credentials);
            loginAttemptService.loginSucceeded(almoxarife.getEmail());
        } catch (Exception e) {
            loginAttemptService.loginFailed(almoxarife.getEmail());
            throw e;
        }

        Almoxarife almoxarifeAutenticado = almoxarifePort.findByEmail(almoxarife.getEmail())
                        .orElseThrow(() -> new ResponseStatusException(404, "Email do Almoxarife não cadastrado", null));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);

        //ultimo acesso para almoxarifado
        almoxarifeAutenticado.setUltimoAcesso(LocalDateTime.now());
        almoxarifePort.save(almoxarifeAutenticado);

        return AlmoxarifeMapper.toEntity(almoxarifeAutenticado, token);
    }

    public AlmoxarifeResponse atualizarParcial(Integer id, AlmoxarifeUpdateRequest request) {
        Almoxarife almoxarife = almoxarifePort.findById(id)
                .orElseThrow(() -> new EntidadeNaoExisteException("Almoxarife não encontrado"));

        if (request.nome() != null && !request.nome().isBlank()) {
            almoxarife.setNome(request.nome());
        }

        if (request.email() != null && !request.email().isBlank()
                && !request.email().equalsIgnoreCase(almoxarife.getEmail())) {
            boolean emailEmUso = almoxarifePort.findByEmail(request.email())
                    .filter(a -> !a.getId().equals(id))
                    .isPresent();
            if (emailEmUso) {
                throw new EntidadeConflictException("Já existe um almoxarife com esse e-mail");
            }
            almoxarife.setEmail(request.email());
        }

        if (request.telefone() != null && !request.telefone().isBlank()) {
            almoxarife.setTelefone(request.telefone());
        }


        if (request.idAlmoxarifado() != null) {
            Almoxarifado novo = almoxarifadoPort.findById(request.idAlmoxarifado())
                    .orElseThrow(() -> new EntidadeNaoExisteException("Almoxarifado não encontrado"));
            almoxarife.setAlmoxarifado(novo);
        }

        return AlmoxarifeMapper.toResponse(almoxarifePort.save(almoxarife));
    }

    public void alterarSenha(Integer id, AlmoxarifeSenhaRequest request) {
        Almoxarife almoxarife = almoxarifePort.findById(id)
                .orElseThrow(() -> new EntidadeNaoExisteException("Almoxarife não encontrado"));

        if (!encoder.matches(request.senhaAntiga(), almoxarife.getSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha antiga incorreta");
        }

        if (encoder.matches(request.senhaNova(), almoxarife.getSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A nova senha deve ser diferente da antiga");
        }

        almoxarife.setSenha(encoder.encode(request.senhaNova()));
        almoxarifePort.save(almoxarife);
    }
}
