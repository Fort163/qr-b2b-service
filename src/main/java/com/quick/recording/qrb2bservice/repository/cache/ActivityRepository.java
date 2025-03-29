package com.quick.recording.qrb2bservice.repository.cache;

import com.quick.recording.gateway.dto.company.ActivityDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActivityRepository extends CrudRepository<ActivityDto, UUID> {

}
