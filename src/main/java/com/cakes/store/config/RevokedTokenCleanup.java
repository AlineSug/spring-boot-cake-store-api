package com.cakes.store.config;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RevokedTokenCleanup {

    private final RevokedTokenRepository revokedTokenRepository;

    @Scheduled(fixedRate = 30 * 60 * 1000)
    public void purgeExpiredEntries(){
        revokedTokenRepository.deleteAllExpired(LocalDateTime.now());
    }
}
