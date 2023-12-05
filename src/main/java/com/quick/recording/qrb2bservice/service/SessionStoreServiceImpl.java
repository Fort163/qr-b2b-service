package com.quick.recording.qrb2bservice.service;

import com.quick.recording.gateway.config.error.exeption.NotFoundException;
import com.quick.recording.qrb2bservice.entity.SessionStoreEntity;
import com.quick.recording.qrb2bservice.repository.SessionStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionStoreServiceImpl implements SessionStoreService {

    private final SessionStoreRepository sessionStoreRepository;

    @Override
    public String getByUuid(UUID uuid) {
        return sessionStoreRepository.findById(uuid).orElseThrow(() -> new NotFoundException(SessionStoreEntity.class, uuid)).getStore();
    }

    @Override
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
