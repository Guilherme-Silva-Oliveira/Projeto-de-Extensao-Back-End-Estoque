package school.sptech.sistema_estoque.dto.estoque.almoxarife;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.enums.Role;

import java.time.LocalDateTime;

public record AlmoxarifeResponse(
        @Schema(description = "ID do Almoxarife") Integer id,
        @Schema(description = "Nome do Almoxarife") String nome,
        @Schema(description = "Email do Almoxarife") String email,
        @Schema(description = "Telefone do Almoxarife") String telefone,
        @Schema(description = "Data de Criação do Almoxarife") LocalDateTime dataCriacao,
        @Schema(description = "Último Acesso do Almoxarife") LocalDateTime ultimoAcesso,
        @Schema(description = "Status do Usuário") Boolean statusUsuario,
        @Schema(description = "Almoxarifado Associado") AlmoxarifadoResponse almoxarifado,
        @Schema(description = "Perfil de Acesso") Role role
) {}
