package com.matchpointecv.matchpointecv.jogo;

import com.matchpointecv.matchpointecv.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "hora")
    private LocalDateTime hora;

    @Column(name = "local")
    private String local;

    @Column(name = "max_participantes")
    private int maxParticipantes;

    @ManyToOne
    @JoinColumn(name = "criador_id", referencedColumnName = "id")
    private Usuario criadorId;

    @ManyToMany
    @JoinTable(
            name = "jogo_usuarios",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;

}
