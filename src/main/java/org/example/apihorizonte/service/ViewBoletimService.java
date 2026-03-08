package org.example.apihorizonte.service;

import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.viewBoletim.ViewBoletimResponseDTO;
import org.example.apihorizonte.model.ViewBoletim;
import org.example.apihorizonte.repository.ViewBoletimRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ViewBoletimService {
    private final ViewBoletimRepository viewBoletimRepository;

    public List<ViewBoletimResponseDTO> getAllBoletim() {
        return viewBoletimRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }


    public List<ViewBoletimResponseDTO> getBoletimByDisciplina(String disciplina) {
        return viewBoletimRepository.findByDisciplina(disciplina)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<ViewBoletimResponseDTO> getBoletimByAluno(Long alunoId) {
        return viewBoletimRepository.findByAlunoId(alunoId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private ViewBoletimResponseDTO toResponseDTO(ViewBoletim viewBoletim) {

        ViewBoletimResponseDTO dto = new ViewBoletimResponseDTO();

        dto.setId(viewBoletim.getId());

        dto.setAlunoId(viewBoletim.getAluno().getId());
        dto.setAlunoNome(viewBoletim.getAluno().getUsuario().getNome());

        dto.setBimestre(viewBoletim.getBimestre());
        dto.setDisciplina(viewBoletim.getDisciplina());
        dto.setMediaFinal(viewBoletim.getMediaFinal());

        return dto;
    }
}
