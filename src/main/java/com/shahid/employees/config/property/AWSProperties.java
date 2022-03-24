package com.shahid.employees.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("config.aws")
public class AWSProperties {
    private String accessKey;
    private String secretKey;
    private String region;
}
