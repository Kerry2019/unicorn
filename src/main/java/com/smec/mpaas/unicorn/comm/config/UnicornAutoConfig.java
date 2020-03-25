package com.smec.mpaas.unicorn.comm.config;

import com.smec.mpaas.unicorn.comm.property.SecurityProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties({ SecurityProperty.class})
@ComponentScan(basePackages = {"com.smec.mpaas.unicorn"})
public class UnicornAutoConfig {


}
