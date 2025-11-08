package com.quick.recording.qrb2bservice.service.remote;

import com.quick.recording.gateway.dto.company.ServiceDto;
import com.quick.recording.gateway.main.service.remote.MainRemoteServiceAbstract;
import com.quick.recording.gateway.service.company.CompanyServiceServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteServiceServiceImpl extends MainRemoteServiceAbstract<ServiceDto, CompanyServiceServiceApi>
        implements RemoteServiceService {

    @Autowired
    public RemoteServiceServiceImpl(CompanyServiceServiceApi service) {
        super(service);
    }

    @Override
    public Class<ServiceDto> getType() {
        return ServiceDto.class;
    }

}
