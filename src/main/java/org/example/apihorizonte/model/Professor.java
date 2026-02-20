package org.example.apihorizonte.model;

import jakarta.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String registroFuncional;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRegistroFuncional() {
        return registroFuncional;
    }

    public void setRegistroFuncional(String registroFuncional) {
        this.registroFuncional = registroFuncional;
    }
}
