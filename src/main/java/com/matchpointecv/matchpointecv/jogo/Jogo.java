package com.matchpointecv.matchpointecv.jogo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    private String data;

    @Column(name = "hora")
    private String hora;

    @Column(name = "local")
    private String local;

    @Column(name = "max_participantes")
    private int maxParticipantes;

    @Column(name = "criador_id")
    private Long criador;

    @Column(name = "tipo")
    private String tipo;

}
