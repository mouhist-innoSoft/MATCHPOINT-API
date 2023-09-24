package com.matchpointecv.matchpointecv.usuario;

import com.matchpointecv.matchpointecv.jogo.Jogo;
import com.matchpointecv.matchpointecv.time.Time;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @ManyToMany(mappedBy = "usuarios")
    private List<Jogo> jogos;

    @ManyToMany(mappedBy = "integrantes")
    private List<Time> times;
}
