package com.matchpointecv.matchpointecv.pontuacao;


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
@Table(name = "pontuacao")
public class Pontuacao {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "participante_id")
    private Usuario usuarioId;

    @OneToOne
    @JoinColumn(name = "jogo_id", referencedColumnName = "id")
    private Integer jogoId;

    @Column(name = "pontos")
    private Integer pontos;

}
