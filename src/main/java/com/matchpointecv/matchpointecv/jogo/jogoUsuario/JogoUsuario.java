package com.matchpointecv.matchpointecv.jogo.jogoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "jogo_usuarios")
public class JogoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "jogo_id")
    private Long jogoId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "permicao_jogo_privado")
    private Boolean permicao;

    @Column(name = "presenca_confirmada")
    private Boolean presencaConfirmada;
}
