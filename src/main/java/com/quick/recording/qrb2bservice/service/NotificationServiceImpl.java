package com.quick.recording.qrb2bservice.service;

import com.quick.recording.gateway.dto.notification.mail.MailCodeDto;
import com.quick.recording.gateway.dto.notification.mail.MailResult;
import com.quick.recording.gateway.enumerated.Result;
import com.quick.recording.gateway.enumerated.TemplateEnum;
import com.quick.recording.gateway.service.notification.NotificationServiceMailSenderApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationServiceMailSenderApi mailSenderApi;
    private Map<String, String> mapPhone = new HashMap<>();

    @Override
    @CircuitBreaker(name = "notificationService")
    public Boolean sendCodeEmail(String email) {
        MailCodeDto mailCodeDto = new MailCodeDto();
        mailCodeDto.setEmail(email);
        mailCodeDto.setTemplate(TemplateEnum.QR_B2B_CODE);
        ResponseEntity<MailResult> result = mailSenderApi.code(mailCodeDto);
        return result.getBody().getResult().equals(Result.SUCCESS);
    }

    @Override
    @CircuitBreaker(name = "notificationService")
    public Boolean checkCodeEmail(String email, String code) {
        MailCodeDto mailCodeDto = new MailCodeDto();
        mailCodeDto.setEmail(email);
        mailCodeDto.setCode(code);
        return mailSenderApi.check(mailCodeDto).getBody();
    }

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
