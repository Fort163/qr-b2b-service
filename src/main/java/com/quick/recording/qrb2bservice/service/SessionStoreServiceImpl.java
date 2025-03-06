package com.quick.recording.qrb2bservice.service;

import com.quick.recording.gateway.config.MessageUtil;
import com.quick.recording.gateway.config.error.exeption.NotFoundException;
import com.quick.recording.qrb2bservice.entity.SessionStoreEntity;
import com.quick.recording.qrb2bservice.repository.SessionStoreRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionStoreServiceImpl implements SessionStoreService {

    private final SessionStoreRepository sessionStoreRepository;
    private final MessageUtil messageUtil;

    @Override
    @CircuitBreaker(name = "database")
    public String getByUuid(UUID uuid) {
        return sessionStoreRepository.findById(uuid).orElseThrow(
                () -> new NotFoundException(messageUtil, SessionStoreEntity.class, uuid)
        ).getStore();
    }

    @Override
    @CircuitBreaker(name = "database")
    public boolean save(UUID uuid, String store) {
        try {
            Optional<SessionStoreEntity> byId = sessionStoreRepository.findById(uuid);
            if(byId.isPresent()){
                SessionStoreEntity sessionStoreEntity = byId.get();
                sessionStoreEntity.setStore(store);
                sessionStoreRepository.save(sessionStoreEntity);
            }
            else {
                sessionStoreRepository.save(new SessionStoreEntity(uuid, store));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
