package org.example.apihorizonte.controller;

import org.example.apihorizonte.dto.professor.ProfessorResponseDTO;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;
import org.example.apihorizonte.repository.ProfessorRepository;
import org.example.apihorizonte.service.ProfessorService;
import org.example.apihorizonte.service.UsuarioService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/get")
    public List<ProfessorResponseDTO> get() {
        return professorService.getAllProfessores();
    }
}
