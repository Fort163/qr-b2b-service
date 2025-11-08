package com.quick.recording.qrb2bservice.service.local;

import com.quick.recording.gateway.config.MessageUtil;
import com.quick.recording.gateway.dto.BaseDto;
import com.quick.recording.gateway.dto.company.CompanyDto;
import com.quick.recording.gateway.dto.company.EmployeeDto;
import com.quick.recording.gateway.dto.company.ProfessionDto;
import com.quick.recording.gateway.dto.company.ServiceDto;
import com.quick.recording.gateway.dto.notification.message.NotificationMessageDto;
import com.quick.recording.gateway.dto.user.UserInfoDto;
import com.quick.recording.gateway.enumerated.*;
import com.quick.recording.qrb2bservice.service.remote.*;
import com.quick.recording.resource.service.anatation.WithServerAuth;
import com.quick.recording.resource.service.security.QROAuth2AuthenticatedPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class B2bServiceImpl implements B2bService{

    @Value("${spring.application.name}")
    private String appName;

    private final RemoteCompanyService remoteCompanyService;
    private final RemoteEmployeeService remoteEmployeeService;
    private final RemoteProfessionService remoteProfessionService;
    private final RemoteServiceService remoteServiceService;
    private final RemoteNotificationMessageService remoteNotificationMessageService;
    private final RemoteUserInfoService remoteUserInfoService;
    private final MessageUtil messageUtil;

    @Override
    @WithServerAuth
    public ResponseEntity<CompanyDto> createNewCompany(CompanyDto dto, QROAuth2AuthenticatedPrincipal user) {
        ResponseEntity<CompanyDto> createCompany = null;
        ResponseEntity<ServiceDto> createService = null;
        ResponseEntity<ProfessionDto> createProfession = null;
        ResponseEntity<EmployeeDto> createEmployee = null;
        ResponseEntity<UserInfoDto> updateUserInfo = null;
        ResponseEntity<NotificationMessageDto> createMessage = null;
        try {
            createCompany = remoteCompanyService.post(dto);
            ServiceDto serviceDto = createService(createCompany.getBody());
            createService = remoteServiceService.post(serviceDto);
            ProfessionDto professionDto = createProfession(createCompany.getBody());
            createProfession = remoteProfessionService.post(professionDto);
            EmployeeDto employeeDto = createEmployee(user.getUuid(), createCompany.getBody(), createProfession.getBody());
            createEmployee = remoteEmployeeService.post(employeeDto);
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.setUuid(user.getUuid());
            userInfoDto.setUserId(user.getUuid());
            userInfoDto.setCompanyId(createCompany.getBody().getUuid());
            userInfoDto.setEmployeeId(createEmployee.getBody().getUuid());
            updateUserInfo = remoteUserInfoService.patch(userInfoDto);
            Assert.notNull(updateUserInfo.getBody().getUserId(), "User Uuid cannot be null");
            Assert.notNull(updateUserInfo.getBody().getCompanyId(), "Company Uuid cannot be null");
            Assert.notNull(updateUserInfo.getBody().getEmployeeId(), "Employee Uuid cannot be null");
            NotificationMessageDto messageDto = createMessage(user.getName());
            createMessage = remoteNotificationMessageService.post(messageDto);
        }
        catch (Exception exception){
            if(Objects.isNull(createCompany)){
                log.error("Company create filed");
            } else if(Objects.isNull(createService)){
                log.error("Service create filed");

                log.error("Company try delete");
                remoteCompanyService.delete(createCompany.getBody().getUuid(), Delete.HARD);
            } else if(Objects.isNull(createProfession)){
                log.error("Profession create filed");

                log.error("Service try delete");
                remoteServiceService.delete(createService.getBody().getUuid(), Delete.HARD);
                log.error("Company try delete");
                remoteCompanyService.delete(createCompany.getBody().getUuid(), Delete.HARD);
            } else if(Objects.isNull(createEmployee)){
                log.error("Employee create filed");

                log.error("Profession try delete");
                remoteProfessionService.delete(createProfession.getBody().getUuid(), Delete.HARD);
                log.error("Service try delete");
                remoteServiceService.delete(createService.getBody().getUuid(), Delete.HARD);
                log.error("Company try delete");
                remoteCompanyService.delete(createCompany.getBody().getUuid(), Delete.HARD);
            } else if(Objects.isNull(updateUserInfo)){
                log.error("UserInfo update filed");

                log.error("Employee try delete");
                remoteEmployeeService.delete(createEmployee.getBody().getUuid(), Delete.HARD);
                log.error("Profession try delete");
                remoteProfessionService.delete(createProfession.getBody().getUuid(), Delete.HARD);
                log.error("Service try delete");
                remoteServiceService.delete(createService.getBody().getUuid(), Delete.HARD);
                log.error("Company try delete");
                remoteCompanyService.delete(createCompany.getBody().getUuid(), Delete.HARD);
            }
            log.error(exception.getMessage());
        }
        return createCompany;
    }

    private NotificationMessageDto createMessage(String userName) {
        NotificationMessageDto messageDto = new NotificationMessageDto();
        messageDto.setFromUser(appName);
        messageDto.setToUser(userName);
        messageDto.setSendType(SendType.TO_USER);
        messageDto.setMessageType(MessageType.INFO);
        messageDto.setProject(Project.QR);
        messageDto.setMessageCode("notification.message.you-create-company");
        return messageDto;
    }

    private EmployeeDto createEmployee(UUID uuid, CompanyDto company, ProfessionDto profession) {
        BaseDto baseDto = new BaseDto();
        baseDto.setUuid(company.getUuid());
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setAuthId(uuid);
        employeeDto.setCompany(baseDto);
        employeeDto.setProfession(profession);
        employeeDto.setPermissions(List.of(CompanyHierarchyEnum.OWNER));
        return employeeDto;
    }

    private ProfessionDto createProfession(CompanyDto company) {
        BaseDto baseDto = new BaseDto();
        baseDto.setUuid(company.getUuid());
        ProfessionDto professionDto = new ProfessionDto();
        professionDto.setName(messageUtil.create("default.create.profession.name"));
        professionDto.setDescription(messageUtil.create("default.create.profession.description"));
        professionDto.setCompany(baseDto);
        return professionDto;
    }

    private ServiceDto createService(CompanyDto company) {
        BaseDto baseDto = new BaseDto();
        baseDto.setUuid(company.getUuid());
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setCompany(baseDto);
        serviceDto.setName(messageUtil.create("default.create.service.name"));
        serviceDto.setWorkClock(LocalTime.of(1,0));
        return serviceDto;
    }
}
