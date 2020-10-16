package com.net.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean("mainDataSource")
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource mainDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public DataSourceTransactionManager mainDataSourceTransactionManager(@Qualifier("mainDataSource") DataSource mainDataSource){
        return new DataSourceTransactionManager(mainDataSource);
    }
}
