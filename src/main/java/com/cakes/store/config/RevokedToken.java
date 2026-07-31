package com.cakes.store.config;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "revoked_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RevokedToken {
    @Id
    private String tokenId;

    private LocalDateTime expiration;
}
