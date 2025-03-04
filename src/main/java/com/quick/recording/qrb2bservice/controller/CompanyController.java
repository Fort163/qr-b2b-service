package com.quick.recording.qrb2bservice.controller;

import com.quick.recording.gateway.dto.company.ActivityDto;
import com.quick.recording.gateway.dto.company.CompanyDto;
import com.quick.recording.gateway.dto.company.SearchActivityDto;
import com.quick.recording.qrb2bservice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;


    @GetMapping("/activity")
    @PreAuthorize("isAuthenticated()")
    public Page<ActivityDto> search(SearchActivityDto searchActivityDto, Pageable pageable){
        return companyService.getActivityList(searchActivityDto,pageable);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CompanyDto> post(@RequestBody CompanyDto dto){
        return companyService.createCompany(dto);
    }


}
