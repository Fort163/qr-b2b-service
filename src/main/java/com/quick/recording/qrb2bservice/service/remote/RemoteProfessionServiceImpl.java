package com.quick.recording.qrb2bservice.service.remote;

import com.quick.recording.gateway.dto.company.ProfessionDto;
import com.quick.recording.gateway.main.service.remote.MainRemoteServiceAbstract;
import com.quick.recording.gateway.service.company.CompanyServiceProfessionApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteProfessionServiceImpl extends MainRemoteServiceAbstract<ProfessionDto, CompanyServiceProfessionApi>
        implements RemoteProfessionService {

    @Autowired
    public RemoteProfessionServiceImpl(CompanyServiceProfessionApi service) {
        super(service);
    }

    @Override
    public Class<ProfessionDto> getType() {
        return ProfessionDto.class;
    }

}
