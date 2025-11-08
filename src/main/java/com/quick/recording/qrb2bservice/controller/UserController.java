package com.quick.recording.qrb2bservice.controller;

import com.quick.recording.qrb2bservice.service.temp.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final NotificationService notificationService;

    @GetMapping("/createCodePhone")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<Boolean> createCodePhone(@RequestParam(name = "phone") String phone) {
        return ResponseEntity.ok(notificationService.sendCodePhone(phone));
    }

    @GetMapping("/checkCodePhone")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<Boolean> createCodePhone(@RequestParam(name = "phone") String phone,
                                                   @RequestParam(name = "code") String code) {
        return ResponseEntity.ok(notificationService.checkCodePhone(phone, code));
    }

}