package com.smec.mpaas.unicorn.config;

import com.smec.mpaas.unicorn.property.SecurityProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@Configuration
@EnableConfigurationProperties({ SecurityProperty.class})
@ComponentScan(basePackages = {"com.smec.mpaas.unicorn"})
public class UnicornAutoConfig {


}
