package com.quick.recording.qrb2bservice.service.local;

import com.quick.recording.gateway.dto.company.CompanyDto;
import com.quick.recording.resource.service.security.QROAuth2AuthenticatedPrincipal;
import org.springframework.http.ResponseEntity;

public interface B2bService {

    ResponseEntity<CompanyDto> createNewCompany(CompanyDto dto, QROAuth2AuthenticatedPrincipal user);

}
