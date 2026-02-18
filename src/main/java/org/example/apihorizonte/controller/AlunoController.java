package org.example.apihorizonte.controller;

import org.example.apihorizonte.dto.aluno.AlunoResponseDTO;
import org.example.apihorizonte.service.AlunoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("/get")
    public List<AlunoResponseDTO> get() {
        return alunoService.getAllAlunos();
    }
}
