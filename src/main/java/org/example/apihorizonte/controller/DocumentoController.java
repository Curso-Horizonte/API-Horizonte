package org.example.apihorizonte.controller;

import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.documento.DocumentoRequestDTO;
import org.example.apihorizonte.dto.documento.DocumentoResponseDTO;
import org.example.apihorizonte.openapi.DocumentoOpenAPI;
import org.example.apihorizonte.service.DocumentoService;
import org.example.apihorizonte.validation.OnCreate;
import org.example.apihorizonte.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/documento")
public class DocumentoController implements DocumentoOpenAPI {
    private final DocumentoService documentoService;

    @GetMapping("/get")
    public List<DocumentoResponseDTO> getDocumentos() {
        return documentoService.getAllDocumentos();
    }

    @PostMapping("/add")
    public ResponseEntity<DocumentoResponseDTO> addDocumento(@Validated({OnCreate.class, Default.class}) @RequestBody DocumentoRequestDTO documentoRequestDTO) {
        return ResponseEntity.ok(documentoService.addDocumento(documentoRequestDTO));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<DocumentoResponseDTO> updateDocumento(@PathVariable Long id, @Validated({OnPatch.class}) @RequestBody DocumentoRequestDTO documentoRequestDTO){
        return ResponseEntity.ok(documentoService.updateDocumento(id, documentoRequestDTO));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeDocumento(@PathVariable Long id) {
        documentoService.removeDocumento(id);
        return ResponseEntity.ok("Documento removido com sucesso");
    }

}
