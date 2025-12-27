package com.net.config;

import org.ff4j.FF4j;
import org.ff4j.audit.repository.JdbcEventRepository;
import org.ff4j.property.store.JdbcPropertyStore;
import org.ff4j.security.SpringSecurityAuthorisationManager;
import org.ff4j.store.JdbcFeatureStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FF4JConfig {

    @Bean
    public FF4j ff4j(DataSource dataSource) {
        FF4j ff4j = new FF4j();
        ff4j.setFeatureStore(new JdbcFeatureStore(dataSource));
        ff4j.setEventRepository(new JdbcEventRepository(dataSource));
        ff4j.setPropertiesStore(new JdbcPropertyStore(dataSource));
        ff4j.setAuthorizationsManager(new SpringSecurityAuthorisationManager());
        ff4j.audit(true);
        ff4j.autoCreate(true);
        return ff4j;
    }
}
