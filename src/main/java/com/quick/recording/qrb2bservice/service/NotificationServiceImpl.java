package com.quick.recording.qrb2bservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    private Map<String,String> mapEmail = new HashMap<>();
    private Map<String,String> mapPhone = new HashMap<>();

    @Override
    @CircuitBreaker(name = "notificationService")
    public Boolean sendCodeEmail(String email) {
        mapEmail.put(email,"9999");
        return true;
    }

    @Override
    @CircuitBreaker(name = "notificationService")
    public Boolean checkCodeEmail(String email, String code) {
        return mapEmail.get(email).equals(code);
    }

    @Override
    @CircuitBreaker(name = "notificationService")
    public Boolean sendCodePhone(String phone) {
        mapPhone.put(phone,"9999");
        return true;
    }

    @Override
    @CircuitBreaker(name = "notificationService")
    public Boolean checkCodePhone(String phone, String code) {
        return mapPhone.get(phone).equals(code);
    }
}
