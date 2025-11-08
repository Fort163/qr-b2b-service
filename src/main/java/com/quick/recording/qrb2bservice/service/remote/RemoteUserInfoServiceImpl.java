package com.quick.recording.qrb2bservice.service.remote;

import com.quick.recording.gateway.dto.user.UserInfoDto;
import com.quick.recording.gateway.main.service.remote.MainRemoteServiceAbstract;
import com.quick.recording.gateway.service.user.UserServiceUserInfoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteUserInfoServiceImpl extends MainRemoteServiceAbstract<UserInfoDto, UserServiceUserInfoApi>
        implements RemoteUserInfoService {

    @Autowired
    public RemoteUserInfoServiceImpl(UserServiceUserInfoApi service) {
        super(service);
    }

    @Override
    public Class<UserInfoDto> getType() {
        return UserInfoDto.class;
    }

}
