package org.example.apihorizonte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.disciplina.DisciplinaRequestDTO;
import org.example.apihorizonte.dto.disciplina.DisciplinaResponseDTO;
import org.example.apihorizonte.model.Disciplina;
import org.example.apihorizonte.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DisciplinaService {
    final DisciplinaRepository disciplinaRepository;
    final ObjectMapper objectMapper;


    public List<DisciplinaResponseDTO> getAllDisciplinas() {
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        List<DisciplinaResponseDTO> disciplinaRespondeDTO = new ArrayList<>();

        for (Disciplina disciplina : disciplinas) {
            disciplinaRespondeDTO.add(
                    objectMapper.convertValue(disciplina, DisciplinaResponseDTO.class)
            );
        }

        return disciplinaRespondeDTO;
    }

    public DisciplinaResponseDTO addDisciplina(DisciplinaRequestDTO disciplinaRequestDTO) {
        Disciplina disciplinaModel = new Disciplina();
        disciplinaModel.setNome(disciplinaRequestDTO.getNome());

        Disciplina disciplina = disciplinaRepository.save(disciplinaModel);

        return objectMapper.convertValue(disciplina, DisciplinaResponseDTO.class);
    }

    @Transactional
    public DisciplinaResponseDTO updateDisciplina(Long id, DisciplinaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));

        if (dto.getNome() != null) {
            if (dto.getNome().isBlank()) {
                throw new IllegalArgumentException("O nome não pode ser vazio");
            }
            disciplina.setNome(dto.getNome());
        }

        return objectMapper.convertValue(disciplina, DisciplinaResponseDTO.class);
    }

    public void removeDisciplina(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));

        disciplinaRepository.delete(disciplina);
    }
}