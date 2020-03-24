package com.smec.mpaas.unicorn.plugin.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import com.smec.mpaas.unicorn.plugin.druid.property.DruidProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2020/3/23 11:07
 * @history: 1.2020/3/23 created by kerry.wu
 */
@Configuration
@EnableConfigurationProperties({ DruidProperty.class})
public class DruidConfig {
    @Autowired
    private DruidProperty druidProperty;

    @Bean
    public void openPluginDruid(){
        System.out.println("---------  ^__^   Open Unicorn Plugin Druid !!!");
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        return  new DruidDataSource();
    }


    /**
     * 注册，druid监控 的服务
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("loginUsername",druidProperty.getLoginUsername());
        initParameters.put("loginPassword", druidProperty.getLoginPassword());
        // 禁用HTML页面上的“Reset All”功能
        initParameters.put("resetEnable", "false");
        // IP白名单 (没有配置或者为空，则允许所有访问)
        initParameters.put("allow", "");
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    /**
     * 注册，druid监控 的过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean druidFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("druidFilter");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }


}
