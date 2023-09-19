package com.matchpointecv.matchpointecv.jogo;

import com.matchpointecv.matchpointecv.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

}
