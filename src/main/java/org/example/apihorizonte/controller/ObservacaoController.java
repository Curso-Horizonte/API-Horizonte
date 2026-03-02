package org.example.apihorizonte.controller;

import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.observacao.ObservacaoRequestDTO;
import org.example.apihorizonte.dto.observacao.ObservacaoResponseDTO;
import org.example.apihorizonte.service.ObservacaoService;
import org.example.apihorizonte.validation.OnCreate;
import org.example.apihorizonte.validation.OnPatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/observacao")
public class ObservacaoController {
    private ObservacaoService observacaoService;

    @GetMapping
    public List<ObservacaoResponseDTO> getAllObservacoes() {
        return observacaoService.getAllObservacoes();
    }

    @PostMapping
    public ResponseEntity<ObservacaoResponseDTO> addObservacao(
            @Validated({ OnCreate.class, Default.class }) @RequestBody ObservacaoRequestDTO observacaoRequestDTO) {
        return ResponseEntity.ok(observacaoService.addObservacao(observacaoRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ObservacaoResponseDTO> updateObservacao(@PathVariable Long id,
                                                                  @Validated({ OnPatch.class }) @RequestBody ObservacaoRequestDTO observacaoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(observacaoService.updateObservacao(id, observacaoRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeObservacao(@PathVariable Long id) {
        observacaoService.removeObservacao(id);
        return ResponseEntity.ok("Observacao removida com sucesso");
    }
}
