package org.example.apihorizonte.model;

import jakarta.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String registro_funcional;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRegistro_funcional() {
        return registro_funcional;
    }

    public void setRegistro_funcional(String registro_funcional) {
        this.registro_funcional = registro_funcional;
    }
}
