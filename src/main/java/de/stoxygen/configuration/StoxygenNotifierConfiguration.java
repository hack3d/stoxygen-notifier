package de.stoxygen.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StoxygenNotifierConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(StoxygenNotifierConfiguration.class);

    @Value("${sto2.userservice.url:}")
    private String userServiceUrl;

    public String getUserServiceUrl() {
        return userServiceUrl;
    }
}
