package school.sptech.sistema_estoque.dto.estoque.almoxarife;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;

public class AlmoxarifeToken {
    @Schema(description = "ID do Almoxarife") Integer id;
    @Schema(description = "Nome do Almoxarife") String nome;
    @Schema(description = "Email do Almoxarife") String email;
    @Schema(description = "Telefone do Almoxarife") String telefone;
    @Schema(description = "Almoxarifado Associado") AlmoxarifadoResponse almoxarifado;
    @Schema(description = "Token do Almoxarife") String token;
    public AlmoxarifeToken() {
    }
    public AlmoxarifeToken(Integer id, String nome, String email, String telefone, String token,
            AlmoxarifadoResponse almoxarifado) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.token = token;
        this.almoxarifado = almoxarifado;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public AlmoxarifadoResponse getAlmoxarifado() {
        return almoxarifado;
    }
    public void setAlmoxarifado(AlmoxarifadoResponse almoxarifado) {
        this.almoxarifado = almoxarifado;
    }
}
