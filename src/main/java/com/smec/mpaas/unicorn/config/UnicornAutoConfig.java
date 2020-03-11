package com.smec.mpaas.unicorn.config;

import com.smec.mpaas.unicorn.filter.SecurityFilter;
import com.smec.mpaas.unicorn.property.SecurityProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2020/3/6 23:10
 * @history: 1.2020/3/6 created by kerry.wu
 */
@Configuration
@EnableConfigurationProperties({ SecurityProperty.class})
@ComponentScan(basePackages = {"com.smec.mpaas.unicorn"})
public class UnicornAutoConfig {


}
