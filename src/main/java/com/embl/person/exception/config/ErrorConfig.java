package com.embl.person.exception.config;

import com.embl.person.exception.ErrorPropertyHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

@Configuration
public class ErrorConfig {
    private Logger log = LoggerFactory.getLogger(ErrorConfig.class);

    @Bean
    public ErrorPropertyHolder errorPropertyResolver() {
        MutablePropertySources propertySources = new MutablePropertySources();
        try {
            ResourcePropertySource propertySource = new ResourcePropertySource("classpath:errors.properties");
            propertySources.addFirst(propertySource);
        } catch (IOException e) {
            log.info("", "error_property_resolver", e.getMessage());
        }
        return new ErrorPropertyHolder(propertySources);
    }
}
