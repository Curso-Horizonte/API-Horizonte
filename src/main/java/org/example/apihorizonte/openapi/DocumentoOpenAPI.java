package org.example.apihorizonte.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apihorizonte.dto.documento.DocumentoRequestDTO;
import org.example.apihorizonte.dto.documento.DocumentoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DocumentoOpenAPI {

    @Operation(summary = "Buscar todos os documentos",
            description = "Retorna a lista de todos os documentos cadastrados no sistema")
    @ApiResponse(responseCode = "200",
            description = "Documentos encontrados com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DocumentoResponseDTO.class)))
    List<DocumentoResponseDTO> getDocumentos();


    @Operation(summary = "Cadastrar documento",
            description = "Cria um novo documento com usuário vinculado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Documento cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DocumentoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Dados inválidos para cadastro")
    })
    ResponseEntity<DocumentoResponseDTO> addDocumento(@RequestBody DocumentoRequestDTO documentoRequestDTO);


    @Operation(summary = "Atualizar documento parcialmente",
            description = "Atualiza parcialmente os dados de um documento pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Documento atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DocumentoResponseDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Documento não encontrado"),
            @ApiResponse(responseCode = "400",
                    description = "Dados inválidos para atualização")
    })
    ResponseEntity<DocumentoResponseDTO> updateDocumento(
            @PathVariable Long id,
            @RequestBody DocumentoRequestDTO documentoRequestDTO
    );


    @Operation(summary = "Remover documento",
            description = "Remove um documento do sistema pelo ID informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Documento removido com sucesso"),
            @ApiResponse(responseCode = "404",
                    description = "Documento não encontrado")
    })
    ResponseEntity<String> removeDocumento(@PathVariable Long id);
}