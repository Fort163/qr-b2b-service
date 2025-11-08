package com.quick.recording.qrb2bservice.service.remote;

import com.quick.recording.gateway.dto.company.EmployeeDto;
import com.quick.recording.gateway.main.service.remote.MainRemoteServiceAbstract;
import com.quick.recording.gateway.service.company.CompanyServiceEmployeeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteEmployeeServiceImpl extends MainRemoteServiceAbstract<EmployeeDto, CompanyServiceEmployeeApi>
        implements RemoteEmployeeService {

    @Autowired
    public RemoteEmployeeServiceImpl(CompanyServiceEmployeeApi service) {
        super(service);
    }

    @Override
    public Class<EmployeeDto> getType() {
        return EmployeeDto.class;
    }

}
