package com.matchpointecv.matchpointecv.jogo.jogoUsuario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoUsuarioRepository extends JpaRepository<JogoUsuario, Long> {

    JogoUsuario findByJogoIdAndUsuarioId(Long jogoId, Long usuarioId);

    @Modifying
    @Query(value = "UPDATE JogoUsuario ju SET ju.presencaConfirmada = :presencaConfirmada "
            + "WHERE ju.jogoId = :jogoId AND ju.usuarioId = :usuarioId")
    void updatePresencaConfirmada(Boolean presencaConfirmada, Long jogoId, Long usuarioId);

    @Query("SELECT ju.usuarioId FROM JogoUsuario ju WHERE ju.jogoId = :jogoId")
    List<Long> findAllUsuariosByJogoId(@Param("jogoId") Long jogoId);
}
