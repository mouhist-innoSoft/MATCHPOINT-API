package com.matchpointecv.matchpointecv.time;

import com.matchpointecv.matchpointecv.jogo.Jogo;
import com.matchpointecv.matchpointecv.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "time")
public class Time {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToOne
    @JoinColumn(name = "capitao_id", referencedColumnName = "id")
    private Usuario capitao;

    @OneToOne
    @JoinColumn(name = "jogo_id", referencedColumnName = "id")
    private Jogo jogo;
}
