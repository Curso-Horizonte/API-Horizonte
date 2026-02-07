package org.example.apihorizonte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apihorizonte.dto.professor.ProfessorResponseDTO;
import org.example.apihorizonte.model.Professor;
import org.example.apihorizonte.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {
    final ProfessorRepository professorRepository;
    final ObjectMapper objectMapper;

    public ProfessorService(ProfessorRepository professorRepository, ObjectMapper objectMapper) {
        this.professorRepository = professorRepository;
        this.objectMapper = objectMapper;
    }

    public List<ProfessorResponseDTO> getAllProfessores() {
        List<Professor> professors = professorRepository.findAll();
        List<ProfessorResponseDTO> professorsResponseDTO = new ArrayList<>();

        for (Professor professor : professors) {
            professorsResponseDTO.add(
                    objectMapper.convertValue(professor, ProfessorResponseDTO.class)
            );
        }

        return professorsResponseDTO;
    }
}
