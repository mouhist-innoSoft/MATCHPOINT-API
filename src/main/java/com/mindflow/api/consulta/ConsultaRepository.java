package com.mindflow.api.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPsicologoIdAndDataHoraBetween(Long psicologoId, OffsetDateTime start, OffsetDateTime end);
}
