package org.example.apihorizonte.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apihorizonte.dto.usuario.LoginUsuarioRequestDTO;
import org.example.apihorizonte.dto.usuario.LoginUsuarioResponseDTO;
import org.example.apihorizonte.dto.usuario.SenhaUpdateRequestDTO;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsuarioOpenAPI {

    @Operation(
            summary = "Realizar login",
            description = "Autentica o usuário pelo email e senha. "
                    + "Caso esteja com a senha padrão, o campo 'primeiroLogin' retornará true."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login realizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginUsuarioResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Senha incorreta"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<LoginUsuarioResponseDTO> login(
            @RequestBody LoginUsuarioRequestDTO loginUsuarioRequestDTO
    );

    @Operation(
            summary = "Atualizar senha do usuário",
            description = "Permite alterar a nova senha."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Nova senha inválida"),
            @ApiResponse(responseCode = "401", description = "Senha atual incorreta"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<Void> updateSenha(
            @PathVariable Long id,
            @RequestBody SenhaUpdateRequestDTO dto
    );
}