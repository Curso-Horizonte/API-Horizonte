package org.example.apihorizonte.repository;

import org.example.apihorizonte.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
