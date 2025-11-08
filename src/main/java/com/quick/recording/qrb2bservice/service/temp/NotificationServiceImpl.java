package com.quick.recording.qrb2bservice.service.temp;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private Map<String, String> mapPhone = new HashMap<>();

    @Override
    @CircuitBreaker(name = "notificationService")
    public Boolean sendCodePhone(String phone) {
        mapPhone.put(phone, "9999");
        return true;
    }

    @Override
    @CircuitBreaker(name = "notificationService")
    public Boolean checkCodePhone(String phone, String code) {
        return mapPhone.get(phone).equals(code);
    }
}
