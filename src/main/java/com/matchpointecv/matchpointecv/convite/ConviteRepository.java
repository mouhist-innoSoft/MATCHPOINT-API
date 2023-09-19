package com.matchpointecv.matchpointecv.convite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConviteRepository extends JpaRepository<Convite, Long> {
}
