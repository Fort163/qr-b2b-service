package com.quick.recording.qrb2bservice.service;

import com.quick.recording.gateway.dto.company.ActivityDto;
import com.quick.recording.gateway.dto.company.CompanyDto;
import com.quick.recording.gateway.dto.company.SearchActivityDto;
import com.quick.recording.gateway.service.company.CompanyServiceActivityApi;
import com.quick.recording.gateway.service.company.CompanyServiceCompanyApi;
import com.quick.recording.resource.service.anatation.WithServerAuth;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyServiceActivityApi companyServiceActivityApi;
    private final CompanyServiceCompanyApi companyServiceCompanyApi;

    @Override
    @CircuitBreaker(name = "companyService")
    public Page<ActivityDto> getActivityList(SearchActivityDto searchActivityDto, Pageable pageable) {
        return companyServiceActivityApi.list(searchActivityDto, pageable);
    }

    @Override
    @CircuitBreaker(name = "companyService")
    public ResponseEntity<CompanyDto> createCompany(CompanyDto dto) {
        return companyServiceCompanyApi.post(dto);
    }

}
