package com.example.userservice.external.auth.config;


import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.userservice")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class ClientConfig {

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }

}
