package com.cakes.store.config;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface RevokedTokenRepository extends JpaRepository<RevokedToken, String> {

    @Modifying
    @Transactional
    @Query("DELETE FROM RevokedToken r WHERE r.expiration < :now")
    void deleteAllExpired(LocalDateTime now);
}
