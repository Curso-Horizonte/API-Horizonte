package org.example.apihorizonte.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apihorizonte.dto.aluno.AlunoRequestDTO;
import org.example.apihorizonte.dto.aluno.AlunoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AlunoOpenAPI {

    @Operation(summary = "Buscar todos os alunos",
            description = "Retorna a lista de todos os alunos cadastrados no sistema")
    @ApiResponse(responseCode = "200",
            description = "Alunos encontrados com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AlunoResponseDTO.class)))
    List<AlunoResponseDTO> getAlunos();


    @Operation(summary = "Cadastrar aluno",
            description = "Cria um novo aluno com usuário vinculado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Aluno cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Dados inválidos para cadastro")
    })
    ResponseEntity<AlunoResponseDTO> addAluno(@RequestBody AlunoRequestDTO alunoRequestDTO);


    @Operation(summary = "Atualizar aluno parcialmente",
            description = "Atualiza parcialmente os dados de um aluno pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Aluno atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Aluno não encontrado"),
            @ApiResponse(responseCode = "400",
                    description = "Dados inválidos para atualização")
    })
    ResponseEntity<AlunoResponseDTO> updateAluno(
            @PathVariable Long id,
            @RequestBody AlunoRequestDTO alunoRequestDTO
    );


    @Operation(summary = "Remover aluno",
            description = "Remove um aluno do sistema pelo ID informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Aluno removido com sucesso"),
            @ApiResponse(responseCode = "404",
                    description = "Aluno não encontrado")
    })
    ResponseEntity<String> removeAluno(@PathVariable Long id);
}