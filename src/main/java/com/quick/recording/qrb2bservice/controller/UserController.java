package com.quick.recording.qrb2bservice.controller;

import com.quick.recording.gateway.service.company.CompanyController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CompanyController companyController;

    private Map<String,String> map = new HashMap<>();

    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("ок");
    }

    @GetMapping("/createCodeEmail")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<Boolean> createCodeEmail(@RequestParam(name = "email") String email){
        map.put(email,"9999");
        return ResponseEntity.ok(true);
    }

    @GetMapping("/checkCodeEmail")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<Boolean> createCodeEmail(@RequestParam(name = "email") String email,@RequestParam(name = "code") String code){
        Boolean result = map.get(email).equals(code);
        return ResponseEntity.ok(result);
    }


}
