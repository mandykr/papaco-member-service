package com.papaco.papacomemberservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PapacoMemberServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PapacoMemberServiceApplication.class, args);
    }

}
