package org.example.apihorizonte.controller;

import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.disciplina.DisciplinaRequestDTO;
import org.example.apihorizonte.dto.disciplina.DisciplinaResponseDTO;
import org.example.apihorizonte.service.DisciplinaService;
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
@RequestMapping("/api/disciplina")
public class DisciplinaController {
    private DisciplinaService disciplinaService;

    @GetMapping
    public List<DisciplinaResponseDTO> getDisciplinas() {
        return disciplinaService.getAllDisciplinas();
    }

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> addDisciplina(
            @Validated({ OnCreate.class, Default.class }) @RequestBody DisciplinaRequestDTO disciplinaRequestDTO) {
        return ResponseEntity.ok(disciplinaService.addDisciplina(disciplinaRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> updateDisciplina(@PathVariable Long id,
            @Validated({ OnPatch.class }) @RequestBody DisciplinaRequestDTO disciplinaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(disciplinaService.updateDisciplina(id, disciplinaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeDisciplina(@PathVariable Long id) {
        disciplinaService.removeDisciplina(id);
        return ResponseEntity.ok("Disciplina removida com sucesso");
    }
}
