package org.example.apihorizonte.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apihorizonte.dto.professor.ProfessorRequestDTO;
import org.example.apihorizonte.dto.professor.ProfessorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProfessorOpenAPI {

    @Operation(summary = "Buscar todos os professores",
            description = "Retorna a lista de todos os professores cadastrados no sistema")
    @ApiResponse(responseCode = "200",
            description = "Professores encontrados com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProfessorResponseDTO.class)))
    List<ProfessorResponseDTO> getProfessores();


    @Operation(summary = "Cadastrar professor",
            description = "Cria um novo professor com usuário vinculado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Professor cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Dados inválidos para cadastro")
    })
    ResponseEntity<ProfessorResponseDTO> addProfessor(@RequestBody ProfessorRequestDTO professorRequestDTO);


    @Operation(summary = "Atualizar professor parcialmente",
            description = "Atualiza parcialmente os dados de um professor pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Professor atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorResponseDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Professor não encontrado"),
            @ApiResponse(responseCode = "400",
                    description = "Dados inválidos para atualização")
    })
    ResponseEntity<ProfessorResponseDTO> updateProfessor(
            @PathVariable Long id,
            @RequestBody ProfessorRequestDTO professorRequestDTO
    );


    @Operation(summary = "Remover professor",
            description = "Remove um professor do sistema pelo ID informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Professor removido com sucesso"),
            @ApiResponse(responseCode = "404",
                    description = "Professor não encontrado")
    })
    ResponseEntity<String> removeProfessor(@PathVariable Long id);
}