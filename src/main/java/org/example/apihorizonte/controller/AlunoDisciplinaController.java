package org.example.apihorizonte.controller;

import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.alunoDisciplina.AlunoDisciplinaRequestDTO;
import org.example.apihorizonte.dto.alunoDisciplina.AlunoDisciplinaResponseDTO;
import org.example.apihorizonte.dto.nota.NotaRequestDTO;
import org.example.apihorizonte.dto.nota.NotaResponseDTO;
import org.example.apihorizonte.service.AlunoDisciplinaService;
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
@RequestMapping("/api/aluno_disciplina")
public class AlunoDisciplinaController {
    private AlunoDisciplinaService alunoDisciplinaService;

    @GetMapping
    public List<AlunoDisciplinaResponseDTO> getAllAlunoDisciplina() {
        return alunoDisciplinaService.getAllAlunoDisciplina();
    }

    @PostMapping
    public ResponseEntity<AlunoDisciplinaResponseDTO> addAlunoDisciplina(
            @Validated({ OnCreate.class, Default.class }) @RequestBody AlunoDisciplinaRequestDTO alunoDisciplinaRequestDTO) {
        return ResponseEntity.ok(alunoDisciplinaService.addAlunoDisciplina(alunoDisciplinaRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AlunoDisciplinaResponseDTO> updateAlunoDisciplina(@PathVariable Long id,
                                                      @Validated({ OnPatch.class }) @RequestBody AlunoDisciplinaRequestDTO alunoDisciplinaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoDisciplinaService.updateAlunoDisciplina(id, alunoDisciplinaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAlunoDisciplina(@PathVariable Long id) {
        alunoDisciplinaService.removeAlunoDisciplina(id);
        return ResponseEntity.ok("Aluno removido de disciplina com sucesso");
    }
}

