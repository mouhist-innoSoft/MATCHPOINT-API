package com.matchpointecv.matchpointecv.time;

import com.matchpointecv.matchpointecv.jogo.Jogo;
import com.matchpointecv.matchpointecv.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "time_integrantes",
            joinColumns = @JoinColumn(name = "time_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> integrantes;

    @ManyToMany(mappedBy = "times")
    private List<Jogo> jogos;
}
