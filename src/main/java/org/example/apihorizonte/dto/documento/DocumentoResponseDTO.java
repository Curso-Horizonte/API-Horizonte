package org.example.apihorizonte.dto.documento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoResponseDTO {
    private Long id;
    private String titulo;
    private String conteudo;
    private LocalDateTime data;
    private Long professorDisciplinaId;
}
