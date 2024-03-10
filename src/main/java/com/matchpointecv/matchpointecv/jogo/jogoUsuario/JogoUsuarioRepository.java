package com.matchpointecv.matchpointecv.jogo.jogoUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoUsuarioRepository extends JpaRepository<JogoUsuario, Long> {

    JogoUsuario findByJogoIdAndUsuarioId(Long jogoId, Long usuarioId);
}
