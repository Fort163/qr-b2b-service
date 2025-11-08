package com.quick.recording.qrb2bservice.controller;

import com.quick.recording.qrb2bservice.service.local.SessionStoreService;
import com.quick.recording.resource.service.anatation.CurrentUser;
import com.quick.recording.resource.service.security.QROAuth2AuthenticatedPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionStoreService sessionStoreService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> session(@CurrentUser QROAuth2AuthenticatedPrincipal user) {
        String session = sessionStoreService.getByUuid(user.getUuid());
        return ResponseEntity.ok(session);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> session(@CurrentUser QROAuth2AuthenticatedPrincipal user, @RequestBody String store) {
        return ResponseEntity.ok(this.sessionStoreService.save(user.getUuid(), store));
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> remove(@CurrentUser QROAuth2AuthenticatedPrincipal user) {
        return ResponseEntity.ok(this.sessionStoreService.delete(user.getUuid()));
    }

}
