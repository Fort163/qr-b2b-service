package com.quick.recording.qrb2bservice.controller;

import com.google.common.base.Strings;
import com.quick.recording.gateway.dto.company.CompanyDto;
import com.quick.recording.gateway.service.company.CompanyController;
import com.quick.recording.resource.service.anatation.CurrentUser;
import com.quick.recording.resource.service.security.QROAuth2AuthenticatedPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CompanyController companyController;

    private Map<String,String> mapEmail = new HashMap<>();
    private Map<String,String> mapPhone = new HashMap<>();

    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("ок");
    }

    @GetMapping("/createCodeEmail")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<Boolean> createCodeEmail(@RequestParam(name = "email") String email){
        mapEmail.put(email,"9999");
        return ResponseEntity.ok(true);
    }

    @GetMapping("/checkCodeEmail")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<Boolean> createCodeEmail(@RequestParam(name = "email") String email,@RequestParam(name = "code") String code){
        Boolean result = mapEmail.get(email).equals(code);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/createCodePhone")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<Boolean> createCodePhone(@RequestParam(name = "phone") String phone){
        mapPhone.put(phone,"9999");
        return ResponseEntity.ok(true);
    }

    @GetMapping("/checkCodePhone")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<Boolean> createCodePhone(@RequestParam(name = "phone") String phone,@RequestParam(name = "code") String code){
        Boolean result = mapPhone.get(phone).equals(code);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/test/company")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<List<CompanyDto>> testCompany(@CurrentUser QROAuth2AuthenticatedPrincipal user){
        ResponseEntity<List<CompanyDto>> companyList = companyController.getCompanyList();
        return companyList;
    }

    @GetMapping("/test/combo1")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<List<CompanyDto>> testCombo1(@CurrentUser QROAuth2AuthenticatedPrincipal user,@RequestParam(required = false) String name){
        CompanyDto companyDto1 = new CompanyDto();
        companyDto1.setName("Рога копыта");
        companyDto1.setUuid(UUID.randomUUID());
        CompanyDto companyDto2 = new CompanyDto();
        companyDto2.setName("ОТР 2000");
        companyDto2.setUuid(UUID.randomUUID());
        CompanyDto companyDto3 = new CompanyDto();
        companyDto3.setName("Хорошая");
        companyDto3.setUuid(UUID.randomUUID());
        CompanyDto companyDto4 = new CompanyDto();
        companyDto4.setName("Самая важная и самая красивая на свете компания мечты");
        companyDto4.setUuid(UUID.randomUUID());
        CompanyDto companyDto5 = new CompanyDto();
        companyDto5.setName("Техстрой");
        companyDto5.setUuid(UUID.randomUUID());
        CompanyDto companyDto6 = new CompanyDto();
        companyDto6.setName("Про IT");
        companyDto6.setUuid(UUID.randomUUID());
        List<CompanyDto> companyDtoList = List.of(companyDto1, companyDto2, companyDto3, companyDto4, companyDto5, companyDto6);
        if(Objects.nonNull(name) && !name.isEmpty()) {
            return ResponseEntity.ok(companyDtoList.stream().filter(item -> item.getName().toLowerCase().contains(name.toLowerCase())).toList());
        }
        else {
            return ResponseEntity.ok(companyDtoList);
        }
    }

    @PostMapping("/test/combo2")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<List<CompanyDto>> testCombo2(@CurrentUser QROAuth2AuthenticatedPrincipal user, @RequestBody Map<String,Object> map){
        ResponseEntity<List<CompanyDto>> companyList = companyController.getCompanyList();
        return companyList;
    }

}