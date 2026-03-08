package org.example.apihorizonte.controller;

import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.viewBoletim.ViewBoletimResponseDTO;
import org.example.apihorizonte.service.ViewBoletimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/boletim")
public class ViewBoletimController {

    private ViewBoletimService viewBoletimService;

    @GetMapping
    public List<ViewBoletimResponseDTO> getAllBoletim() {
        return viewBoletimService.getAllBoletim();
    }

    @GetMapping("/disciplina/{disciplina}")
    public List<ViewBoletimResponseDTO> getBoletimByDisciplina(@PathVariable String disciplina) {
        return viewBoletimService.getBoletimByDisciplina(disciplina);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<ViewBoletimResponseDTO> getBoletimByAluno(@PathVariable Long alunoId) {
        return viewBoletimService.getBoletimByAluno(alunoId);
    }
}
