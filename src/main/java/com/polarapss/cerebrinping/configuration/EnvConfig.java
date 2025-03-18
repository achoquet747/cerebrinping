package com.polarapss.cerebrinping.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:.env", ignoreResourceNotFound = true)
public class EnvConfig {

    @Autowired
    private Environment env;

    public String getDbUrl() {
        return env.getProperty("DB_URL");
    }

    public String getDbUsername() {
        return env.getProperty("DB_USERNAME");
    }

    public String getDbPassword() {
        return env.getProperty("DB_PASSWORD");
    }
}