package com.matchpointecv.matchpointecv.participante;



import com.matchpointecv.matchpointecv.jogo.Jogo;
import com.matchpointecv.matchpointecv.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "participante")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuarioId;

    @OneToOne
    @JoinColumn(name = "jogo_id", referencedColumnName = "id")
    private Jogo jogoId;

    @Column(name = "posiçao_preferida")
    private Integer posicaoPreferida;

    @Column(name = "nivel_habilidade")
    private String nivelHabilidade;
}
