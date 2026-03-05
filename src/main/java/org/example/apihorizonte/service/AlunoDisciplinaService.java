package org.example.apihorizonte.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.alunoDisciplina.AlunoDisciplinaRequestDTO;
import org.example.apihorizonte.dto.alunoDisciplina.AlunoDisciplinaResponseDTO;
import org.example.apihorizonte.model.Aluno;
import org.example.apihorizonte.model.AlunoDisciplina;
import org.example.apihorizonte.model.Disciplina;
import org.example.apihorizonte.repository.AlunoDisciplinaRepository;
import org.example.apihorizonte.repository.AlunoRepository;
import org.example.apihorizonte.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AlunoDisciplinaService {

    private final AlunoDisciplinaRepository alunoDisciplinaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    @Transactional
    public List<AlunoDisciplinaResponseDTO> getAllAlunoDisciplina() {

        return alunoDisciplinaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public AlunoDisciplinaResponseDTO addAlunoDisciplina(
            AlunoDisciplinaRequestDTO dto) {

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        boolean exists = alunoDisciplinaRepository
                .existsByAlunoIdAndDisciplinaId(
                        dto.getAlunoId(),
                        dto.getDisciplinaId()
                );

        if (exists) {
            throw new RuntimeException("Aluno já matriculado nesta disciplina");
        }

        AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
        alunoDisciplina.setAluno(aluno);
        alunoDisciplina.setDisciplina(disciplina);

        AlunoDisciplina saved = alunoDisciplinaRepository.save(alunoDisciplina);

        return toResponseDTO(saved);
    }

    @Transactional
    public AlunoDisciplinaResponseDTO updateAlunoDisciplina(
            Long id,
            AlunoDisciplinaRequestDTO dto) {

        AlunoDisciplina alunoDisciplina = alunoDisciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        alunoDisciplina.setAluno(aluno);
        alunoDisciplina.setDisciplina(disciplina);

        return toResponseDTO(alunoDisciplina);
    }

    @Transactional
    public void removeAlunoDisciplina(Long id) {

        AlunoDisciplina alunoDisciplina = alunoDisciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        alunoDisciplinaRepository.delete(alunoDisciplina);
    }

    private AlunoDisciplinaResponseDTO toResponseDTO(AlunoDisciplina ad) {

        AlunoDisciplinaResponseDTO dto = new AlunoDisciplinaResponseDTO();

        dto.setId(ad.getId());

        dto.setAlunoId(ad.getAluno().getId());
        dto.setAlunoNome(ad.getAluno().getUsuario().getNome());

        dto.setDisciplinaId(ad.getDisciplina().getId());
        dto.setDisciplinaNome(ad.getDisciplina().getNome());

        return dto;
    }
}
