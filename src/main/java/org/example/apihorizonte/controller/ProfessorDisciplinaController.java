package org.example.apihorizonte.controller;

import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.nota.NotaRequestDTO;
import org.example.apihorizonte.dto.nota.NotaResponseDTO;
import org.example.apihorizonte.dto.professorDisciplina.ProfessorDisciplinaRequestDTO;
import org.example.apihorizonte.dto.professorDisciplina.ProfessorDisciplinaResponseDTO;
import org.example.apihorizonte.service.ProfessorDisciplinaService;
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
public class ProfessorDisciplinaController {
    private ProfessorDisciplinaService professorDisciplinaService;

    @GetMapping
    public List<ProfessorDisciplinaResponseDTO> getAllProfessorDisciplina() {
        return professorDisciplinaService.getAllProfessorDisciplina();
    }

    @GetMapping("/professor/{id}")
    public List<ProfessorDisciplinaResponseDTO> getDisciplinaByProfessor(@PathVariable Long id) {
        return professorDisciplinaService.getAllDisciplinaByProfessorId(id);
    }

    @PostMapping
    public ResponseEntity<ProfessorDisciplinaResponseDTO> addProfessorDisciplina(
            @Validated({ OnCreate.class, Default.class }) @RequestBody ProfessorDisciplinaRequestDTO professorDisciplinaRequestDTO) {
        return ResponseEntity.ok(professorDisciplinaService.addProfessorDisciplina(professorDisciplinaRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProfessorDisciplinaResponseDTO> updateProfessorDisciplina(@PathVariable Long id,
                                                                 @Validated({ OnPatch.class }) @RequestBody ProfessorDisciplinaRequestDTO professorDisciplinaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(professorDisciplinaService.updateProfessorDisciplina(id, professorDisciplinaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProfessorDisciplina(@PathVariable Long id) {
        professorDisciplinaService.removeProfessorDisciplina(id);
        return ResponseEntity.ok("Nota removida com sucesso");
    }
}
