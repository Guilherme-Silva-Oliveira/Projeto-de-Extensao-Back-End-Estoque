package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeLogin;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeResponse;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeToken;
import school.sptech.sistema_estoque.dto.mapper.AlmoxarifeMapper;
import school.sptech.sistema_estoque.model.estoque.Almoxarife;
import school.sptech.sistema_estoque.service.AlmoxarifeService;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/v1/almoxarifes")
@Tag(name = "Almoxarife",description = "Operações Relacionadas à Almoxarife")
public class AlmoxarifeController {

    public static final String COOKIE_NOME = "authToken";

    @Value("${jwt.validity}")
    private long jwtValidity;

    private final AlmoxarifeService service;
    public AlmoxarifeController(AlmoxarifeService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um Almoxarife")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "400",description = "Almoxarifado Não Encontrado"),
            @ApiResponse(responseCode = "201",description = "Almoxarife Cadastrado")
    })
    @PostMapping
    public ResponseEntity<AlmoxarifeResponse> cadastrarAlmoxarife(@RequestBody AlmoxarifeRequest request){
        return ResponseEntity.status(201).body(AlmoxarifeMapper.toResponse(service.cadastrarAlmoxarife(request)));
    }

    @Operation(summary = "Listar Todos os Almoxarifes")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Almoxarife Encontrado"),
            @ApiResponse(responseCode = "200",description = "Almoxarifes Encontrados")
    })
    @GetMapping
    public ResponseEntity<List<AlmoxarifeResponse>> listarAlmoxarifes(){
        var almoxarifes = service.listarAlmoxarifes();
        if (almoxarifes.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(almoxarifes.stream().map(AlmoxarifeMapper::toResponse).toList());
    }

    @Operation(summary = "Excluir Almoxarife")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhum Almoxarife Encontrado"),
            @ApiResponse(responseCode = "204",description = "Almoxarife Excluído")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAlmoxarife(@PathVariable Integer id){
        service.excluirAlmoxarife(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AlmoxarifeResponse> login(
            @RequestBody AlmoxarifeLogin loginDto,
            HttpServletResponse response) {
        
        final Almoxarife almoxarife = AlmoxarifeMapper.toEntity(loginDto);
            
        // gera o token internamente, para o cookie
        AlmoxarifeToken autenticado = this.service.autenticar(almoxarife);

        // gera o cookie
        ResponseCookie cookie = ResponseCookie.from(COOKIE_NOME, autenticado.getToken())
                .httpOnly(true)                          // inacessível ao JavaScript
                .secure(false)                           // true em produção (exige HTTPS)
                .sameSite("Lax")                      // bloqueia envio cross-site (mitiga CSRF)
                .path("/")                               // valido para toda a aplicacao
                .maxAge(Duration.ofSeconds(jwtValidity)) // expira junto com o token JWT
                .build();

        // adiciona cookie no reader da resposta
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        AlmoxarifeResponse almoxarifeResponse = AlmoxarifeMapper.toResponse(autenticado);

        return ResponseEntity.status(200).body(almoxarifeResponse);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(COOKIE_NOME, "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(0)  
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.noContent().build();
    }
}
