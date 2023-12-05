package com.quick.recording.qrb2bservice.controller;

import com.quick.recording.gateway.dto.auth.AuthUserDto;
import com.quick.recording.gateway.dto.auth.Role2UserDto;
import com.quick.recording.gateway.service.auth.AuthServiceUserApi;
import com.quick.recording.qrb2bservice.config.ServerAuth;
import com.quick.recording.resource.service.anatation.CurrentUser;
import com.quick.recording.resource.service.security.QROAuth2AuthenticatedPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthServiceUserApi authServiceUserApi;

    private Map<String,String> mapEmail = new HashMap<>();
    private Map<String,String> mapPhone = new HashMap<>();

    @GetMapping("/test")
    public ResponseEntity<String> test(@CurrentUser QROAuth2AuthenticatedPrincipal user){
        Role2UserDto dto = new Role2UserDto();
        dto.setUser(user.getUuid());
        dto.setRole(UUID.fromString("bff8779f-ecf6-4dcf-98d2-17b2984a0934"));
        authServiceUserApi.addRole(dto);
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

    @PatchMapping("/change")
    @PreAuthorize("hasAnyAuthority('ROLE_CHANGE_ME_INFO')")
    public ResponseEntity<AuthUserDto> change(@RequestBody AuthUserDto userDto){
        return authServiceUserApi.patch(userDto);
    }

}