package com.matchpointecv.matchpointecv.participante;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "participante")
public class Participante {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Integer usuarioId;


    @ManyToMany
    @JoinColumn(name = "jogo_id", referencedColumnName = "id")
    private Integer jogoId;

    @Column(name = "posiçao_preferida")
    private Integer posicaoPreferida;

    @Column(name = "nivel_habilidade")
    private String nivelHabilidade;
}
