package com.quick.recording.qrb2bservice.service;

import com.quick.recording.gateway.dto.auth.AuthUserDto;
import com.quick.recording.gateway.service.auth.AuthServiceUserApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthServiceUserApi authServiceUserApi;

    @Override
    @CircuitBreaker(name = "userService")
    public ResponseEntity<AuthUserDto> changeUser(AuthUserDto userDto) {
        return authServiceUserApi.patch(userDto);
    }
}
