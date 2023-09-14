package com.matchpointecv.matchpointecv.jogo;

import com.matchpointecv.matchpointecv.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "jogo")
public class Jogo {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "hora")
    private LocalDate hora;

    @Column(name = "local")
    private String local;

    @Column(name = "max_participantes")
    private int maxParticipantes;

    @ManyToOne
    @JoinColumn(name = "criador_id", referencedColumnName = "id")
    private Usuario criadorId;

}
