package com.quick.recording.qrb2bservice.controller;

import com.quick.recording.gateway.dto.company.CompanyDto;
import com.quick.recording.qrb2bservice.service.local.B2bService;
import com.quick.recording.resource.service.anatation.CurrentUser;
import com.quick.recording.resource.service.security.QROAuth2AuthenticatedPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final B2bService service;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CompanyDto> post(@RequestBody CompanyDto dto, @CurrentUser QROAuth2AuthenticatedPrincipal user) {
        return service.createNewCompany(dto, user);
    }


}
