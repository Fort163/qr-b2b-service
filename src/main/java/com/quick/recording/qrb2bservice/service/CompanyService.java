package com.quick.recording.qrb2bservice.service;

import com.quick.recording.gateway.dto.company.ActivityDto;
import com.quick.recording.gateway.dto.company.CompanyDto;
import com.quick.recording.gateway.dto.company.SearchActivityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CompanyService {

    Page<ActivityDto> getActivityList(SearchActivityDto searchActivityDto, Pageable pageable);

    ResponseEntity<CompanyDto> createCompany(CompanyDto dto);
}
