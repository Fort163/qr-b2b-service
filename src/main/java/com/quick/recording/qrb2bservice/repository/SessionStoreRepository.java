package com.quick.recording.qrb2bservice.repository;

import com.quick.recording.qrb2bservice.entity.SessionStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionStoreRepository extends JpaRepository<SessionStoreEntity, UUID> {
}
