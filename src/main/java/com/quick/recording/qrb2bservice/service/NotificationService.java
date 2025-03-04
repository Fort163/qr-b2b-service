package com.quick.recording.qrb2bservice.service;

public interface NotificationService {

    Boolean sendCodeEmail(String email);

    Boolean checkCodeEmail(String email, String code);

    Boolean sendCodePhone(String phone);

    Boolean checkCodePhone(String phone, String code);

}
