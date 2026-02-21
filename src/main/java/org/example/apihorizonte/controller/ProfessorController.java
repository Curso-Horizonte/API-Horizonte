package org.example.apihorizonte.controller;

import jakarta.validation.groups.Default;
import org.example.apihorizonte.dto.professor.ProfessorRequestDTO;
import org.example.apihorizonte.dto.professor.ProfessorResponseDTO;
import org.example.apihorizonte.service.ProfessorService;
import org.example.apihorizonte.validation.OnCreate;
import org.example.apihorizonte.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    @GetMapping("/get")
    public List<ProfessorResponseDTO> getProfessores() {
        return professorService.getAllProfessores();
    }

    @PostMapping("/add")
    public ResponseEntity<ProfessorResponseDTO> addProfessor(@Validated({OnCreate.class, Default.class}) @RequestBody ProfessorRequestDTO professorRequestDTO) {
        return ResponseEntity.ok(professorService.addProfessor(professorRequestDTO));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizarProfessorParcial(@PathVariable Long id, @Validated({OnPatch.class}) @RequestBody ProfessorRequestDTO professorRequestDTO){
        return ResponseEntity.ok(professorService.updatePrfessor(id, professorRequestDTO));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeProfessor(@PathVariable Long id) {
        professorService.removeProfessor(id);
        return ResponseEntity.ok("Professor removido com sucesso");
    }
}
