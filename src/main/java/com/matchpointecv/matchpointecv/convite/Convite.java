package com.matchpointecv.matchpointecv.convite;


import com.matchpointecv.matchpointecv.jogo.Jogo;
import com.matchpointecv.matchpointecv.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "convite")
public class Convite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rementente_id", referencedColumnName = "id")
    private  Usuario remetente;


    @ManyToOne
    @JoinColumn(name = "jogo_id", referencedColumnName = "id")
    private Jogo jogo;

    @Column(name = "status")
    private String status;

    @ManyToMany
    @JoinTable(
            name = "convite_destinatario",
            joinColumns = @JoinColumn(name = "convite_id"),
            inverseJoinColumns = @JoinColumn(name = "destinatario_id")
    )
    private List<Usuario> destinatarios;
}

