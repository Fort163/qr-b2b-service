package com.quick.recording.qrb2bservice.service;

import com.quick.recording.gateway.dto.broker.MessageChangeDataDto;
import com.quick.recording.gateway.dto.company.ActivityDto;
import com.quick.recording.gateway.main.service.remote.CacheableMainRemoteServiceAbstract;
import com.quick.recording.gateway.service.company.CompanyServiceActivityApi;
import com.quick.recording.qrb2bservice.repository.cache.ActivityRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyActivityServiceImpl extends CacheableMainRemoteServiceAbstract<ActivityDto>
        implements CompanyActivityService {

    public CompanyActivityServiceImpl() {
    }

    @Autowired
    public CompanyActivityServiceImpl(ActivityRepository repository,
                                      CompanyServiceActivityApi service) {
        super(repository, service);
    }

    @Override
    public Class<ActivityDto> getType() {
        return ActivityDto.class;
    }

    @Override
    protected void doUpdatedMessage(MessageChangeDataDto dto) {
        System.out.println(dto);
        super.doUpdatedMessage(dto);
    }
}
