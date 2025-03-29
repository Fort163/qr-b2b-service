package com.quick.recording.qrb2bservice.controller;

import com.quick.recording.gateway.dto.company.ActivityDto;
import com.quick.recording.gateway.service.company.CompanyServiceActivityApi;
import com.quick.recording.qrb2bservice.repository.cache.ActivityRepository;
import com.quick.recording.qrb2bservice.service.CompanyActivityServiceImpl;
import com.quick.recording.qrb2bservice.service.SessionStoreService;
import com.quick.recording.resource.service.anatation.CurrentUser;
import com.quick.recording.resource.service.security.QROAuth2AuthenticatedPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionStoreService sessionStoreService;
    private final StreamBridge streamBridge;
    private final CompanyActivityServiceImpl companyActivityService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_READ')")
    public ResponseEntity<String> session(@CurrentUser QROAuth2AuthenticatedPrincipal user) {
        String session = sessionStoreService.getByUuid(user.getUuid());
        return ResponseEntity.ok(session);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_READ')")
    public ResponseEntity<Boolean> session(@CurrentUser QROAuth2AuthenticatedPrincipal user, @RequestBody String store) {
        return ResponseEntity.ok(this.sessionStoreService.save(user.getUuid(), store));
    }

    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('ROLE_READ')")
    public ResponseEntity<ActivityDto> test() {
        return companyActivityService.byUuid(UUID.fromString("99badf2e-a830-4dc0-a355-f8e290f177e0"));
    }

}
