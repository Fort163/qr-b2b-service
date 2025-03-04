package com.quick.recording.qrb2bservice.controller;

import com.quick.recording.gateway.dto.auth.AuthUserDto;
import com.quick.recording.qrb2bservice.service.NotificationService;
import com.quick.recording.qrb2bservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final NotificationService notificationService;

    @GetMapping("/createCodeEmail")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<Boolean> createCodeEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(notificationService.sendCodeEmail(email));
    }

    @GetMapping("/checkCodeEmail")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<Boolean> createCodeEmail(@RequestParam(name = "email") String email,
                                                   @RequestParam(name = "code") String code) {
        return ResponseEntity.ok(notificationService.checkCodeEmail(email, code));
    }

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

    @PatchMapping("/change")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<AuthUserDto> change(@RequestBody AuthUserDto userDto) {
        return userService.changeUser(userDto);
    }

}