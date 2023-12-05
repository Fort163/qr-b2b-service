package com.quick.recording.qrb2bservice.service;

import java.util.UUID;

public interface SessionStoreService {

    String getByUuid(UUID uuid);

    boolean save(UUID uuid, String store);

}
