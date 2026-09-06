package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import school.sptech.sistema_estoque.config.GerenciadorTokenJwt;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeResponse;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeToken;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeUpdateRequest;
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
        Almoxarife almoxarife = new Almoxarife(null, request.nome(), request.email(), request.telefone(), novaSenha, Role.ALMOXARIFE, almoxarifado);
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
        // 1. Verifica no cache se o e-mail atingiu o limite de tentativas (bloqueio temporário)
        if (loginAttemptService.isBlocked(almoxarife.getEmail())) {
            throw new UsuarioBloqueadoException("Conta temporariamente bloqueada por excesso de tentativas. Tente novamente mais tarde.");
        }

        final UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(almoxarife.getEmail(), almoxarife.getSenha());

        final Authentication authentication;
        try {
            // Tenta validar as credenciais
            authentication = this.authenticationManager.authenticate(credentials);
            // 2. Se as credenciais estiverem corretas, limpa o histórico de falhas do usuário
            loginAttemptService.loginSucceeded(almoxarife.getEmail());
        } catch (Exception e) {
            // 3. Se a autenticação falhar (senha errada, etc.), incrementa as falhas no cache
            loginAttemptService.loginFailed(almoxarife.getEmail());
            throw e;
        }

        Almoxarife almoxarifeAutenticado = almoxarifePort.findByEmail(almoxarife.getEmail())
                        .orElseThrow(() -> new ResponseStatusException(404, "Email do Almoxarife não cadastrado", null));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return AlmoxarifeMapper.toEntity(almoxarifeAutenticado, token);
    }

    public AlmoxarifeResponse atualizarParcial(Integer id, AlmoxarifeUpdateRequest request) {
        Almoxarife almoxarife = almoxarifePort.findById(id).orElseThrow(() -> new EntidadeInvalidException("Almoxarife não encontrado"));
        if (request.nome() != null) {almoxarife.setNome(request.nome());}
        if (request.telefone() != null) {almoxarife.setTelefone(request.telefone());}
        if (request.senha() != null) {almoxarife.setSenha(request.senha());}
        if (request.idAlmoxarifado() != null) {
            Almoxarifado novoAlmoxarifado = almoxarifadoPort.findById(request.idAlmoxarifado())
                .orElseThrow(() -> new EntidadeInvalidException("Almoxarifado não encontrado"));
            almoxarife.setAlmoxarifado(novoAlmoxarifado);
        }
        Almoxarife salvo = almoxarifePort.save(almoxarife);
        return AlmoxarifeMapper.toResponse(salvo);
    }
}
