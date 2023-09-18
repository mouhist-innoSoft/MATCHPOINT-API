package com.matchpointecv.matchpointecv.convite;

import com.matchpointecv.matchpointecv.jogo.Jogo;
import com.matchpointecv.matchpointecv.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "convite")
public class Convite {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rementente_id", referencedColumnName = "id")
    private Usuario remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", referencedColumnName = "id")
    private Usuario destinatario;

    @ManyToOne
    @JoinColumn(name = "jogo_id", referencedColumnName = "id")
    private Jogo jogo;

    @Column(name = "status")
    private String status;
}

