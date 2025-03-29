package com.quick.recording.qrb2bservice.controller;

import com.quick.recording.gateway.dto.company.ActivityDto;
import com.quick.recording.gateway.dto.company.CompanyDto;
import com.quick.recording.qrb2bservice.service.CompanyActivityService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyActivityService companyActivityService;

    @Autowired
    public CompanyController(CompanyActivityService companyActivityService) {
        this.companyActivityService = companyActivityService;
    }

    @GetMapping("/activity")
    @PreAuthorize("isAuthenticated()")
    public Page<ActivityDto> search(@SpringQueryMap ActivityDto dto, Pageable pageable) {
        return companyActivityService.search(dto,pageable);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CompanyDto> post(@RequestBody CompanyDto dto) {
        return ResponseEntity.ok().build();
    }


}
