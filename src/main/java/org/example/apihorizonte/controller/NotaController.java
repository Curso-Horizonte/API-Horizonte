package org.example.apihorizonte.controller;

import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.nota.NotaRequestDTO;
import org.example.apihorizonte.dto.nota.NotaResponseDTO;
import org.example.apihorizonte.dto.observacao.ObservacaoRequestDTO;
import org.example.apihorizonte.dto.observacao.ObservacaoResponseDTO;
import org.example.apihorizonte.service.NotaService;
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
@RequestMapping("/api/nota")
public class NotaController {
    private NotaService notaService;

    @GetMapping
    public List<NotaResponseDTO> getAllNotas() {
        return notaService.getAllNotas();
    }

    @GetMapping("aluno/{alunoId}/disciplina/{disciplinaId}")
    public List<NotaResponseDTO> getNotasByAlunoAndDisciplina(@PathVariable Long alunoId, @PathVariable Long disciplinaId) {
        return notaService.getNotasByAlunoAndDisciplina(alunoId, disciplinaId);
    }

    @PostMapping
    public ResponseEntity<NotaResponseDTO> addNota(
            @Validated({ OnCreate.class, Default.class }) @RequestBody NotaRequestDTO notaRequestDTO) {
        return ResponseEntity.ok(notaService.addNota(notaRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NotaResponseDTO> updateNota(@PathVariable Long id,
                                                                  @Validated({ OnPatch.class }) @RequestBody NotaRequestDTO notaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notaService.updateNota(id, notaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeNota(@PathVariable Long id) {
        notaService.removeNota(id);
        return ResponseEntity.ok("Nota removida com sucesso");
    }
}
