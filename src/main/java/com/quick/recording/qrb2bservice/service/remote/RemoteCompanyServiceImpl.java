package com.quick.recording.qrb2bservice.service.remote;

import com.quick.recording.gateway.dto.company.CompanyDto;
import com.quick.recording.gateway.main.service.remote.MainRemoteServiceAbstract;
import com.quick.recording.gateway.service.company.CompanyServiceCompanyApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteCompanyServiceImpl extends MainRemoteServiceAbstract<CompanyDto, CompanyServiceCompanyApi>
        implements RemoteCompanyService {

    @Autowired
    public RemoteCompanyServiceImpl(CompanyServiceCompanyApi service) {
        super(service);
    }

    @Override
    public Class<CompanyDto> getType() {
        return CompanyDto.class;
    }

}
