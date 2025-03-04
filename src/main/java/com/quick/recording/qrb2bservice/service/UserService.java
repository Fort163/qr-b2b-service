package com.quick.recording.qrb2bservice.service;

import com.quick.recording.gateway.dto.auth.AuthUserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<AuthUserDto> changeUser(AuthUserDto userDto);

}
