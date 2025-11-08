package com.quick.recording.qrb2bservice.service.local;

import java.util.UUID;

public interface SessionStoreService {

    String getByUuid(UUID uuid);

    boolean save(UUID uuid, String store);

    boolean delete(UUID uuid);

}
