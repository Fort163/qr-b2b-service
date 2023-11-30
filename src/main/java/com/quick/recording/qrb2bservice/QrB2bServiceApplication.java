package com.quick.recording.qrb2bservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class} ,scanBasePackages = {"com.quick.recording"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.quick.recording.gateway.service")
public class QrB2bServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrB2bServiceApplication.class, args);
	}

}
