package org.example.apihorizonte.controller;

import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.aluno.AlunoResponseDTO;
import org.example.apihorizonte.dto.aluno.AlunoRequestDTO;
import org.example.apihorizonte.dto.aluno.AlunoResponseDTO;
import org.example.apihorizonte.service.AlunoService;
import org.example.apihorizonte.validation.OnCreate;
import org.example.apihorizonte.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping("/get")
    public List<AlunoResponseDTO> getAlunos() {
        return alunoService.getAllAlunos();
    }

    @PostMapping("/add")
    public ResponseEntity<AlunoResponseDTO> addAluno(@Validated({OnCreate.class, Default.class}) @RequestBody AlunoRequestDTO alunoRequestDTO) {
        return ResponseEntity.ok(alunoService.addAluno(alunoRequestDTO));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarAlunoParcial(@PathVariable Long id, @Validated({OnPatch.class}) @RequestBody AlunoRequestDTO alunoRequestDTO){
        return ResponseEntity.ok(alunoService.updateAluno(id, alunoRequestDTO));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeAluno(@PathVariable Long id) {
        alunoService.removeAluno(id);
        return ResponseEntity.ok("Aluno removido com sucesso");
    }

}
