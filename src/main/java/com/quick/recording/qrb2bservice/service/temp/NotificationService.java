package com.quick.recording.qrb2bservice.service.temp;

public interface NotificationService {

    Boolean sendCodePhone(String phone);

    Boolean checkCodePhone(String phone, String code);

}
