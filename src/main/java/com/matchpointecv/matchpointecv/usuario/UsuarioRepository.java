package com.matchpointecv.matchpointecv.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAllByIdIn(List<Long> ids);

    Usuario findByNome(String nome);

    Usuario findByEmail(String email);
}
