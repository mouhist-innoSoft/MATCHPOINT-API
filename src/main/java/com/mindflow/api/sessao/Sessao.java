package com.mindflow.api.sessao;

import com.mindflow.api.consulta.Consulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "consulta_id", nullable = false, unique = true)
    private Consulta consulta;

    @Column(name = "resumo_atendimento")
    private String resumoAtendimento;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "data_registro", nullable = false)
    private OffsetDateTime dataRegistro;
}
