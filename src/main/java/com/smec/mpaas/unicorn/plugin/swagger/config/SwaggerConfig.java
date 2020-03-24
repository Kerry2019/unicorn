package com.smec.mpaas.unicorn.plugin.swagger.config;

import com.smec.mpaas.unicorn.comm.property.SecurityProperty;
import com.smec.mpaas.unicorn.plugin.swagger.property.SwaggerProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableSwagger2
@EnableConfigurationProperties({ SwaggerProperty.class})
public class SwaggerConfig {
    @Resource
    private SecurityProperty securityProperty;
    @Resource
    private SwaggerProperty swaggerProperty;

    @Bean
    public void openPluginSwagger(){
        System.out.println("---------  ^__^   Open Unicorn Plugin Swagger !!!");
    }

    @Bean
    public Docket productApi(){
        Docket docket= new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
        if(securityProperty.isOpen()){
            docket=docket.securitySchemes(apiKeyList());
        }
        return docket;
    }

    /**
     * title、description、version 可以通过配置文件注入
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(swaggerProperty.getTitle())
                .description(swaggerProperty.getDescription())
                .version(swaggerProperty.getVersion())
                .build();
    }

    /**
     * header 可以通过配置文件注入
     * @return
     */
    private List<ApiKey> apiKeyList(){
        return Arrays.asList(
                new ApiKey("token",securityProperty.getHeaderName(),"header")
        );
    }
}
